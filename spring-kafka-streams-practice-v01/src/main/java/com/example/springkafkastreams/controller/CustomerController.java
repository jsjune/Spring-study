package com.example.springkafkastreams.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/{customerId}/total-amount")
    public ResponseEntity<String> getTotalAmount(@PathVariable("customerId") String customerId) {
        String total = redisTemplate.opsForValue().get("total:" + customerId);
        return ResponseEntity.ok(total);
    }
}

