package com.example.springkafkastreams.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSumConsumer {

    private final RedisTemplate<String, String> redisTemplate;

    @KafkaListener(topics = "customer-order-sum", groupId = "sum-consumer")
    public void consume(ConsumerRecord<String, String> record) {
        System.out.println("record.value() = " + record.value());
        redisTemplate.opsForValue().set("total:" + record.key(), record.value());
    }
}
