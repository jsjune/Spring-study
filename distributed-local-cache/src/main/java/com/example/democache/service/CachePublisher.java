package com.example.democache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CachePublisher {
    private final StringRedisTemplate redisTemplate;

    public void publishInvalidateMessage(String cacheName) {
        System.out.println("📢 캐시 초기화 메시지 발행: " + cacheName);
        redisTemplate.convertAndSend("cache:invalidate", cacheName);
    }
}
