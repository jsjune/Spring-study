package com.example.springkafkastreams.producer;

import com.example.springkafkastreams.dto.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendOrder(String customerId, int amount) {
        OrderRequest order = new OrderRequest(customerId, amount);
        try {
            String json = objectMapper.writeValueAsString(order);
            kafkaTemplate.send("order-events", customerId, json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

