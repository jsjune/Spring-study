package com.example.reactorkafkatest.controller;

import com.example.reactorkafkatest.dto.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import reactor.kafka.sender.SenderResult;

@Slf4j
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaProducerController {

    private final KafkaSender<String, String> kafkaSender;

    /**
     * 테스트 메시지를 Kafka 토픽으로 발송합니다.
     * @param request 토픽 이름, 메시지 내용, 보낼 횟수를 담은 DTO
     * @return 발송 결과
     */
    @PostMapping("/send")
    public Mono<ResponseEntity<String>> sendMessage(@RequestBody MessageRequest request) {
        log.info("Sending {} messages to topic {}", request.count(), request.topic());

        // 1. 0부터 count-1까지의 숫자를 Flux 스트림으로 만듭니다.
        Flux<SenderRecord<String, String, Integer>> outboundFlux = Flux.range(0, request.count())
                .map(i -> {
                    // 2. 메시지 키를 "key-" + 숫자로 설정합니다.
                    //    키가 다르면 다른 파티션으로 분산될 확률이 높아져 병렬 처리 테스트에 유리합니다.
                    String key = "key-" + i;
                    String value = request.message() + "-" + i;
                    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(request.topic(), null, value);

                    // 3. SenderRecord로 감싸줍니다. (세 번째 인자는 passthrough 데이터로, 결과 처리 시 사용 가능)
                    return SenderRecord.create(producerRecord, i);
                });

        // 4. KafkaSender를 사용하여 메시지를 비동기적으로 발송합니다.
        return kafkaSender.send(outboundFlux)
                .doOnError(e -> log.error("Failed to send messages", e))
                .doOnNext(result -> {
                    // 각 메시지 발송 결과 로깅 (성공 시)
                    log.info("Successfully sent message #{} to offset {}",
                            result.correlationMetadata(), // SenderRecord 생성 시 넣었던 i 값
                            result.recordMetadata().offset());
                })
                .then(Mono.just(ResponseEntity.ok("Successfully sent " + request.count() + " messages.")))
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().body("Failed to send messages: " + e.getMessage())));
    }
}
