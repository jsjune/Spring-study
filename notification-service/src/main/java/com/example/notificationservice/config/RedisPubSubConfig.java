package com.example.notificationservice.config;

import com.example.notificationservice.notification.service.redis.RedisSseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@RequiredArgsConstructor
public class RedisPubSubConfig {

    private final RedisSseService sseService;

    /*
    - RedisMessageListenerContainer는 Redis의 Pub/Sub 기능을 사용하여 메시지를 구독하는 컨테이너입니다.
    - connectionFactory를 사용하여 Redis에 연결합니다.
    - "sse-notifications" 채널에 대한 메시지를 구독하도록 설정합니다.
    - 메시지가 수신되면 redisMessageListener(sseService)가 실행됩니다.
    * */
    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(redisMessageListener(sseService), new PatternTopic("sse-notifications"));
        return container;
    }

    /*
    - MessageListenerAdapter를 사용하여 Redis에서 발행된 메시지를 처리할 리스너를 정의합니다.
    - 익명 람다 함수를 사용하여, 메시지를 문자열(receivedMessage)로 변환 후 RedisSseService.consumeMessage(receivedMessage)를 호출합니다.
    - 즉, Redis에서 PUBLISH sse-notifications "Hello" 같은 메시지가 오면 consumeMessage("Hello")를 실행합니다.
    * */
    @Bean
    public MessageListenerAdapter redisMessageListener(RedisSseService sseService) {
        return new MessageListenerAdapter((MessageListener) (message, pattern) -> {
            String receivedMessage = new String(message.getBody());
            sseService.consumeMessage(receivedMessage);
        });
    }

}

