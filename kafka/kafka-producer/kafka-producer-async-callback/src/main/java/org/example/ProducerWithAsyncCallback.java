package org.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerWithAsyncCallback {
	private static final Logger logger = LoggerFactory.getLogger(ProducerWithAsyncCallback.class);
	private static final String TOPIC_NAME = "test";
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";

	public static void main(String[] args) {
		Properties configs = new Properties();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

		ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "key1", "value1");

		producer.send(record, new ProducerCallback());

		logger.info("{}", record);
		producer.flush();
		producer.close();
	}
}
