package com.sample2.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
@EnableR2dbcRepositories
@EnableR2dbcAuditing
public class R2dbcConfig implements ApplicationListener<ApplicationReadyEvent>, ApplicationRunner {
	private final DatabaseClient databaseClient;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		databaseClient.sql("SELECT 1").fetch().one()
			.subscribe(
				success -> {
					log.info("Initialize r2dbc database connection.");
				},
				error -> {
					log.error("Failed to initialize r2dbc database connection.");
					SpringApplication.exit(event.getApplicationContext(), () -> -110);
				}
			);
	}

	@Override
	public void run(ApplicationArguments args) {
		deleteTables();
		createTables();
	}


	private void createTables() {
		log.info("createTables");
		// users 테이블 생성
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

		// posts 테이블 생성
		Mono<Void> createIndexMono = databaseClient.sql("CREATE INDEX idx_user_id ON posts (user_id)")
			.fetch()
			.rowsUpdated()
			.then();

		databaseClient.sql("CREATE TABLE posts (" +
				"id BIGINT auto_increment PRIMARY KEY, " +
				"user_id BIGINT, " +
				"title VARCHAR(255), " +
				"content TEXT, " +
				"created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
				"updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
				")")
			.fetch()
			.rowsUpdated()
			// .then(createIndexMono)
			.block();
	}

	private void deleteTables() {
		log.info("deleteTables");
		databaseClient.sql("DROP TABLE IF EXISTS users")
			.fetch()
			.rowsUpdated()
			.block();

		databaseClient.sql("DROP TABLE IF EXISTS posts")
			.fetch()
			.rowsUpdated()
			.block();
	}
}
