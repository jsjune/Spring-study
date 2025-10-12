# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.4.10 application using Reactor Kafka (Project Reactor-based Kafka client) to demonstrate reactive Kafka message production and consumption. The application uses Java 21 and Spring WebFlux for reactive processing.

## Build and Run Commands

**Build the project:**
```bash
./gradlew build
```

**Run tests:**
```bash
./gradlew test
```

**Run the application:**
```bash
./gradlew bootRun
```

**Run a single test:**
```bash
./gradlew test --tests ReactorKafkaTestApplicationTests
```

## Project Structure

```
src/main/java/com/example/reactorkafkatest/
├── ReactorKafkaTestApplication.java   # Main Spring Boot application
├── config/
│   └── KafkaConfig.java               # Kafka sender/receiver configuration
├── controller/
│   └── KafkaProducerController.java   # REST endpoints for sending messages
├── consumer/
│   ├── KafkaConsumer.java             # Consumer for test-topic-01
│   └── KafkaConsumer2.java            # Consumer for test-topic-02 and related topics
└── dto/
    └── MessageRequest.java            # DTO for message send requests
```

## Architecture

### Reactive Kafka Configuration Pattern

The application uses a **multi-receiver pattern** where separate `KafkaReceiver` beans are created for different consumer groups and topic subscriptions:

- `KafkaConfig` (src/main/java/com/example/reactorkafkatest/config/KafkaConfig.java:1) creates:
  - `test01Receiver`: Consumes from `test-topic-01` with group `test-01-group`
  - `test02Receiver`: Consumes from `test-topic-02`, `test-topic-02_1`, `test-topic-02_2` with group `test-02-group`
  - `kafkaSender`: Shared producer for all message sending

### Consumer Pattern

Both `KafkaConsumer` and `KafkaConsumer2` (in `consumer` package) use identical reactive processing patterns:

1. **Partition Grouping**: Messages are grouped by `topicPartition` using `groupBy()`
2. **Sequential Processing per Partition**: Within each partition, messages are processed sequentially using `concatMap()` to maintain ordering
3. **Manual Offset Commit**: Each message's offset is committed individually after successful processing via `record.receiverOffset().commit()`
4. **Concurrency Control**: `KafkaConsumer` uses `concatMap(record -> ..., 6)` for processing up to 6 partitions concurrently

### Producer Pattern

`KafkaProducerController` (src/main/java/com/example/reactorkafkatest/controller/KafkaProducerController.java:1) exposes a REST endpoint `/kafka/send` that:

- Accepts `MessageRequest` with topic, message, and count
- Creates a `Flux.range()` of messages with different keys to distribute across partitions
- Uses `kafkaSender.send()` to publish messages reactively
- Returns a reactive `Mono<ResponseEntity<String>>` with the result

## Key Configuration Details

**Kafka Cluster** (application.yml):
- Bootstrap servers: 3-node cluster at 192.168.137.100 (ports 30268, 30724, 31861)

**Consumer Settings**:
- Auto-commit disabled (`enable.auto.commit: false`) - manual commit required
- Long session timeout (180000ms) and poll interval (600000ms) for processing heavy workloads
- Static group membership available via `group.instance.id` property

**Producer Settings**:
- Idempotence enabled (`enable.idempotence: true`)
- Acknowledgment level: `all` (strongest durability guarantee)

## Important Patterns to Follow

### When Adding New Consumers

1. Create a new `KafkaReceiver` bean in `config/KafkaConfig.java` with unique bean name, group ID, and topic subscription
2. Create a consumer component in `consumer/` package with `@Qualifier` annotation to inject the specific receiver bean
3. Use `groupBy(topicPartition)` to ensure per-partition ordering
4. Always commit offsets after successful processing: `record.receiverOffset().commit()`
5. Handle errors appropriately to avoid committing offsets for failed messages

### When Modifying Processing Logic

The `concatMap()` operator's prefetch parameter (e.g., `concatMap(record -> ..., 6)`) controls how many partitions are processed concurrently. Adjust this based on:
- Number of topic partitions
- Processing time per message
- Available resources

### Context Propagation

The codebase includes `io.micrometer:context-propagation` dependency. Commented-out code shows where context propagation can be applied using `ContextSnapshot.captureAll().updateContext(context)` in the `.contextWrite()` operator.
