package com.example.springkafkastreams;

import com.example.springkafkastreams.dto.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Properties;

public class OrderAggregator {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        Properties props = buildProperties();
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> orders = builder.stream(
                "order-events",
                Consumed.with(Serdes.String(), Serdes.String())
        );

        KTable<String, Integer> customerSums = orders
                .mapValues(OrderAggregator::extractAmount)
                .groupByKey()
                .reduce(
                        Integer::sum,
                        Materialized.<String, Integer, KeyValueStore<Bytes, byte[]>>as("customer-sum-store")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(Serdes.Integer())
                );

        customerSums
                .toStream()
                .mapValues(String::valueOf)
                .to("customer-order-sum", Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }

    private static Integer extractAmount(String json) {
        try {
            OrderRequest order = objectMapper.readValue(json, OrderRequest.class);
            System.out.println(">>> amount = " + order.amount());
            return order.amount();
        } catch (Exception e) {
            System.err.println("Failed to parse order: " + json);
            return 0;
        }
    }

    private static Properties buildProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "order-aggregator");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return props;
    }
}
