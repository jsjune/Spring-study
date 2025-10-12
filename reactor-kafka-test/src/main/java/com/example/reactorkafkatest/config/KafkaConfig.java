package com.example.reactorkafkatest.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.consumer.properties.group.instance.id}")
    private String groupInstanceId;

    /**
     * Reactor Kafka Sender를 위한 Bean을 생성합니다.
     * 이 Bean은 애플리케이션 전체에서 메시지를 발송하는 데 사용됩니다.
     *
     * @param kafkaProperties application.yml의 spring.kafka.producer.* 설정
     * @return KafkaSender 인스턴스
     */
    @Bean
    public KafkaSender<String, String> kafkaSender(KafkaProperties kafkaProperties) {
        // application.yml의 producer 설정을 사용하여 SenderOptions 생성
        SenderOptions<String, String> senderOptions =
                SenderOptions.create(kafkaProperties.buildProducerProperties());

        return KafkaSender.create(senderOptions);
    }

    /**
     * test01 관련 토픽을 처리하는 KafkaReceiver 빈.
     * Consumer Group: "test-01-group"
     * Topics: "test-topic-01"
     */
    @Bean
    public KafkaReceiver<String, String> test01Receiver(KafkaProperties kafkaProperties) {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-01-group");
        props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, groupInstanceId);

        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.<String, String>create(props)
                .subscription(List.of("test-topic-01"));

        return KafkaReceiver.create(receiverOptions);
    }

    /**
     * test02 관련 토픽을 처리하는 KafkaReceiver 빈.
     * Consumer Group: "test-02-group"
     * Topics: "test-topic-02", "test-topic-02_1", "test-topic-02_2"
     */
    @Bean
    public KafkaReceiver<String, String> test02Receiver(KafkaProperties kafkaProperties) {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-02-group");
        props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, groupInstanceId);

        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.<String, String>create(props)
                .subscription(List.of("test-topic-02", "test-topic-02_1", "test-topic-02_2"));

        return KafkaReceiver.create(receiverOptions);
    }
}
