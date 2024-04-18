package org.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.ToString;

public class SimpleProducer {
	private static final Logger logger = LoggerFactory.getLogger(SimpleProducer.class);
	private static final String TOPIC_NAME = "my-topic2";
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";

	public static void main(String[] args) {

		Properties configs = new Properties();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put("log.retention.ms", "86400000");

		KafkaProducer<String, String> producer = new KafkaProducer<>(configs);

		ObjectMapper mapper = new ObjectMapper();
		String jsonStringToProduce;
		try {
			jsonStringToProduce = mapper.writeValueAsString(new Person("가나", 5));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		// String messageValue = "testMessage";

		ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, jsonStringToProduce);
		producer.send(record);
		logger.info("{}", record);
		producer.flush();
		producer.close();
	}

	@ToString
	@Data
	public static class Person {
		private String name;
		private int age;

		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}


	}
}
