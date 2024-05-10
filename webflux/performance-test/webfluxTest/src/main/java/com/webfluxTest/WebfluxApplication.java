package com.webfluxTest;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
@Slf4j
public class WebfluxApplication implements ApplicationRunner {
	private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
	private final UserRepository userRepository;
	private final DatabaseClient databaseClient;

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@GetMapping("/health")
	public Mono<Map<String, String>> health() {
		return Mono.just(Map.of("health", "ok"));
	}

	@GetMapping("/users/1/cache")
	public Mono<Map<String, String>> getCachedUser() {
		var name = reactiveRedisTemplate.opsForValue().get("users:1:name");
		var email = reactiveRedisTemplate.opsForValue().get("users:1:email");

		return Mono.zip(name, email)
			.map(i -> Map.of("name", i.getT1(), "email", i.getT2()));
	}

	@GetMapping("/users/{id}")
	public Mono<User> getUser(@PathVariable("id") Long id) {
		return userRepository.findById(id).defaultIfEmpty(new User());
	}

	@Override
	public void run(ApplicationArguments args) {
		deleteTables();
		createTables();
	}


	private void createTables() {
		log.info("createTables");
		databaseClient.sql("CREATE TABLE users (" +
				"id BIGINT auto_increment PRIMARY KEY, " +
				"name VARCHAR(255), " +
				"email VARCHAR(255), " +
				"created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
				"updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
				")")
			.fetch()
			.rowsUpdated()
			.block();
	}

	private void deleteTables() {
		log.info("deleteTables");
		databaseClient.sql("DROP TABLE IF EXISTS users")
			.fetch()
			.rowsUpdated()
			.block();
	}
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
class User {
	@Id
	private Long id;
	private String name;
	private String email;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime updatedAt;
}

interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
