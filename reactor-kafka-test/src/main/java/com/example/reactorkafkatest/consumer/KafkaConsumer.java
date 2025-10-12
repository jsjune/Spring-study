package com.example.reactorkafkatest.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;

@Slf4j
@Component
public class KafkaConsumer {

    private final KafkaReceiver<String, String> receiver;

    // ✅ 직접 생성자를 만들고, 파라미터에 @Qualifier를 붙여줍니다.
    public KafkaConsumer(@Qualifier("test01Receiver") KafkaReceiver<String, String> receiver) {
        this.receiver = receiver;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void consumeMessages() {
        this.receiver.receive()
                .groupBy(record -> record.receiverOffset().topicPartition())
                .flatMap(partitionFlux ->
                        partitionFlux.concatMap(record -> {
                            log.info("Thread: {}, Topic: {}, Partition: {}, Offset: {}, Value: {}",
                                    Thread.currentThread().getName(),
                                    record.topic(),
                                    record.partition(),
                                    record.offset(),
                                    record.value());

                            // [수정됨] 비즈니스 로직 처리 후 반드시 오프셋을 커밋합니다.
                            return processRecord(record.value())
                                    .then(record.receiverOffset().commit()) // ✅ 오프셋 커밋 추가!
                                    .doOnError(e -> log.error("Error processing record, offset will not be committed for {}-{}",
                                            record.partition(), record.offset(), e));
                        }, 6)
                )
                // context-propagation은 여기서 전체 스트림에 적용하는 것이 더 효과적일 수 있습니다.
                // .contextWrite(context -> ContextSnapshot.captureAll().updateContext(context))
                .subscribe();
        log.info("Started consuming from test-topic-01...");
    }

    private Mono<Void> processRecord(String value) {
        // 비즈니스 로직 처리 (예: 10ms 지연 시뮬레이션)
        return Mono.delay(java.time.Duration.ofMillis(10)).then();
    }
}
