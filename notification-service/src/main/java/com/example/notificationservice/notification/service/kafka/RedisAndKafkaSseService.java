package com.example.notificationservice.notification.service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisAndKafkaSseService {
    private final StringRedisTemplate redisTemplate;
    private final Map<String, SseEmitter> localEmitters = new ConcurrentHashMap<>();
    private static final String SSE_KEY_PREFIX = "sse:client:";

    // SSE 연결 추가 (Redis에 clientId 저장)
    public SseEmitter addEmitter(String clientId) {
        long timeout = 10 * 60 * 1000L;
        SseEmitter emitter = new SseEmitter(timeout);
        localEmitters.put(clientId, emitter);
        redisTemplate.opsForValue().set(SSE_KEY_PREFIX + clientId, "active",timeout, TimeUnit.MILLISECONDS); // Redis에 SSE 연결 정보 저장
        emitter.onCompletion(() -> removeEmitter(clientId));
        emitter.onTimeout(() -> removeEmitter(clientId));
        return emitter;
    }

    // SSE 연결 제거
    private void removeEmitter(String clientId) {
        localEmitters.remove(clientId);
        redisTemplate.delete(SSE_KEY_PREFIX + clientId); // Redis에서 삭제
    }

    // Redis를 통해 현재 SSE 클라이언트가 존재하는지 확인
    public boolean isClientConnected(String clientId) {
        return redisTemplate.hasKey(SSE_KEY_PREFIX + clientId);
    }

    // SSE 메시지 전송 (Kafka 메시지를 수신했을 때 호출됨)
    public void sendMessage(String clientId, String message) {
        if (isClientConnected(clientId)) {
            SseEmitter emitter = localEmitters.get(clientId);
            if (emitter != null) {
                try {
                    emitter.send(SseEmitter.event().data(message));
                    System.out.println("📢 SSE 메시지 전송 (Client ID: " + clientId + ") - " + message);
                } catch (Exception e) {
                    emitter.complete();
                    removeEmitter(clientId);
                }
            }
        } else {
            System.out.println("⚠️ 클라이언트 " + clientId + "에 대한 SSE 연결이 없음 (분산 환경).");
        }
    }
}
