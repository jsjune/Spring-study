package org.example;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerWithSyncOffsetCommit {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerWithSyncOffsetCommit.class);
	private static final String TOPIC_NAME = "test";
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	private static final String GROUP_ID = "test-group";
	private static KafkaConsumer<String, String> consumer;

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());

		Properties configs = new Properties();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

		consumer = new KafkaConsumer<>(configs);
		consumer.subscribe(Arrays.asList(TOPIC_NAME));

		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
				for (ConsumerRecord<String, String> record : records) {
					logger.info("{}", record);
				}
				consumer.commitSync();
			}
		} catch (WakeupException e) {
			logger.warn("Wakeup consumer");
		} finally {
			logger.warn("Consumer close");
			consumer.close();
		}
	}

	static class ShutdownThread extends Thread {
		public void run() {
			logger.info("Shutdown hook");
			consumer.wakeup();
		}
	}
}
