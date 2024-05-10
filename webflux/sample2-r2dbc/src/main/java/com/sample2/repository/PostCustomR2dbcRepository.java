package com.sample2.repository;

import com.sample2.domain.Post;

import reactor.core.publisher.Flux;

public interface PostCustomR2dbcRepository {
	Flux<Post> findAllByUserId(Long id);
}
