package com.example.democache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheInvalidateListener implements MessageListener {
    private final CacheManager cacheManager;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String cacheKey = new String(message.getBody());
        System.out.println("📢 캐시 초기화 메시지 수신: " + cacheKey);

        for (String key : cacheKey.split("-")) {
            // 전체 목록 캐시 삭제 (수정 & 삭제 시 사용)
            cacheManager.getCache("myCache").evict(key);
            System.out.println("🚀 로컬 캐시 초기화 완료: " + key);
        }
    }
}
