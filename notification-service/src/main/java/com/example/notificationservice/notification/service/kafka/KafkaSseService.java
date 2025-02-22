package com.example.notificationservice.notification.service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSseService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RedisAndKafkaSseService sseService;

    // Kafka 메시지를 수신하면 SSE로 푸시
    @KafkaListener(topics = "notification-topic")
    public void consumeMessage(String message) {
        System.out.println("Kafka 메시지 수신: " + message);
        String clientId = message.split("-")[0];
        String msg = message.split("-")[1];

        // Redis를 통해 클라이언트가 연결된 서버에서 SSE 전송
        sseService.sendMessage(clientId, msg);
    }

    public String producer(String clientId, String message) {
        String payload = clientId + "-" + message;
        kafkaTemplate.send("notification-topic", payload);
        return payload;
    }
}
