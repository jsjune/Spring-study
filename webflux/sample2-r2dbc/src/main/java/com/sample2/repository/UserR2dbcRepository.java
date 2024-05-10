package com.sample2.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.sample2.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserR2dbcRepository extends ReactiveCrudRepository<User, Long> {
	Flux<User> findByName(String name);

	Flux<User> findByNameOrderByIdDesc(String name);

	@Modifying
	@Query("DELETE FROM users Where name = :name")
	Mono<Void> deleteByName(String name);

}
