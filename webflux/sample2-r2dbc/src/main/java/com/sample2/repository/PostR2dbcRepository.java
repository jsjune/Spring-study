package com.sample2.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.sample2.domain.Post;

import reactor.core.publisher.Flux;

public interface PostR2dbcRepository extends ReactiveCrudRepository<Post, Long>, PostCustomR2dbcRepository {
}
