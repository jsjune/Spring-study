package org.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionProducer {
	private static final Logger logger = LoggerFactory.getLogger(TransactionProducer.class);
	private static final String TOPIC_NAME = "test";
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";

	public static void main(String[] args) {

		Properties configs = new Properties();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		configs.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "my-transaction-id");

		KafkaProducer<String, String> producer = new KafkaProducer<>(configs);
		producer.initTransactions();
		producer.beginTransaction();
		String messageValue = "testMessage";
		ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, messageValue);
		producer.send(record);
		logger.info("{}", record);
		producer.flush();


		producer.commitTransaction();
		//producer.abortTransaction();

		producer.close();
	}
}
