package com.sample2.service;

import org.springframework.stereotype.Service;

import com.sample2.domain.Post;
import com.sample2.repository.PostR2dbcRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostR2dbcRepository postRepository;

	public Mono<Post> create(Long userId, String title, String content) {
		return postRepository.save(
			Post.builder()
				.userId(userId)
				.title(title)
				.content(content)
				.build()
		);
	}

	public Flux<Post> findAll() {
		return postRepository.findAll();
	}

	public Mono<Post> findById(Long id) {
		return postRepository.findById(id);
	}

	public Flux<Post> findAllByUserId(Long id) {
		return postRepository.findAllByUserId(id);
	}

	public Mono<Void> deleteById(Long id) {
		return postRepository.deleteById(id);
	}

}
