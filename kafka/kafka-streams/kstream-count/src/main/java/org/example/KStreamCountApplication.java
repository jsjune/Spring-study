package org.example;

import java.time.Duration;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KStreamCountApplication {
	private static final Logger log = LoggerFactory.getLogger(KStreamCountApplication.class);

	private static final String APPLICATION_NAME = "stream-count-application";
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	private static final String TEST_LOG = "test";

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, APPLICATION_NAME);
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10000);

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> stream = builder.stream(TEST_LOG);
		KTable<Windowed<String>, Long> countTable = stream.groupByKey()
			.windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
			.count();
		countTable.toStream().foreach(((key, value) -> {
			log.info(key.key() + " is [" + key.window().startTime() + "~" + key.window().endTime() + "] count : " + value);
		}));

		KafkaStreams streams = new KafkaStreams(builder.build(), props);
		streams.start();

	}
}
