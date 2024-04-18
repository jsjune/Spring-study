package org.example;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.ListConsumerGroupsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaAdminClient {
	private static final Logger logger = LoggerFactory.getLogger(KafkaAdminClient.class);
	private static final String BOOTSTRAP_SERVERS = "localhost:9092";

	public static void main(String[] args) throws Exception {

		Properties configs = new Properties();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		AdminClient admin = AdminClient.create(configs);
		logger.info("== Get broker information");
		for (Node node : admin.describeCluster().nodes().get()) {
			logger.info("node : {}", node);
			ConfigResource cr = new ConfigResource(ConfigResource.Type.BROKER, node.idString());
			DescribeConfigsResult describeConfigs = admin.describeConfigs(Collections.singleton(cr));
			describeConfigs.all().get().forEach((broker, config) -> {
				config.entries().forEach(configEntry -> logger.info(configEntry.name() + "= " + configEntry.value()));
			});
		}

		logger.info("== Get default num.partitions");
		for (Node node : admin.describeCluster().nodes().get()) {
			ConfigResource cr = new ConfigResource(ConfigResource.Type.BROKER, node.idString());
			DescribeConfigsResult describeConfigs = admin.describeConfigs(Collections.singleton(cr));
			Config config = describeConfigs.all().get().get(cr);
			Optional<ConfigEntry> optionalConfigEntry = config.entries()
				.stream()
				.filter(v -> v.name().equals("num.partitions"))
				.findFirst();
			ConfigEntry numPartitionConfig = optionalConfigEntry.orElseThrow(Exception::new);
			logger.info("{}", numPartitionConfig.value());
		}

		logger.info("== Topic list");
		for (TopicListing topicListing : admin.listTopics().listings().get()) {
			logger.info("{}", topicListing.toString());
		}

		logger.info("== test topic information");
		Map<String, TopicDescription> topicInformation = admin.describeTopics(Collections.singletonList("test"))
			.all()
			.get();
		logger.info("{}", topicInformation);

		logger.info("== Consumer group list");
		ListConsumerGroupsResult listConsumerGroups = admin.listConsumerGroups();
		listConsumerGroups.all().get().forEach(v -> {
			logger.info("{}", v);
		});

		System.out.println("========================================================================");

		try (AdminClient adminClient = AdminClient.create(configs)) {
			// Create a new topic with custom configuration
			NewTopic newTopic = new NewTopic("my-topic", 1, (short) 1);
			newTopic.configs(Collections.singletonMap(TopicConfig.RETENTION_MS_CONFIG, "10000")); // 1일 (24시간)
			adminClient.createTopics(Collections.singletonList(newTopic)).all().get();

			// Describe the topic to verify the configuration
			TopicDescription topicDescription = adminClient.describeTopics(Collections.singletonList("my-topic")).all().get().get("my-topic");
			System.out.println("Topic Configurations:");
			System.out.println(topicDescription.name());
		}

		admin.close();
	}
}
