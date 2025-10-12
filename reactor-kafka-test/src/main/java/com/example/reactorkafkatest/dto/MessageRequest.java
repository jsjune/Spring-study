package com.example.reactorkafkatest.dto;

public record MessageRequest(
        String topic,
        String message,
        int count
) {}
