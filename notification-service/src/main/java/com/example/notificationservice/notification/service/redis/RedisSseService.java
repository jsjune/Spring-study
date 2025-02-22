package com.example.notificationservice.notification.service.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class RedisSseService {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final StringRedisTemplate redisTemplate;
    private final ChannelTopic sseTopic = new ChannelTopic("sse-notifications");

    // SSE 클라이언트 연결 저장
    public SseEmitter addEmitter(String clientId) {
        SseEmitter emitter = new SseEmitter(10 * 60 * 1000L); // 10분 유지
        emitters.put(clientId, emitter);
        emitter.onCompletion(() -> emitters.remove(clientId));
        emitter.onTimeout(() -> emitters.remove(clientId));
        return emitter;
    }

    public void consumeMessage(String message) {
        System.out.println("redis 메시지 수신: " + message);
        String clientId = message.split("-")[0];
        String msg = message.split("-")[1];

        // 모든 SSE 클라이언트에게 메시지 전송
        SseEmitter emitter = emitters.get(clientId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().data(msg));
            } catch (Exception e) {
                emitter.complete();
                emitters.remove(clientId);
            }
        }
    }

    public String producer(String clientId, String message) {
        String payload = clientId + "-" + message;
        redisTemplate.convertAndSend(sseTopic.getTopic(), payload); // Redis Pub/Sub으로 전송
        return payload;
    }
}
