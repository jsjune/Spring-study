package com.sample2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample2.dto.PostCreateRequest;
import com.sample2.dto.PostResponse;
import com.sample2.service.PostService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	@PostMapping
	public Mono<PostResponse> createPost(@RequestBody PostCreateRequest request) {
		return postService.create(request.getUserId(), request.getTitle(), request.getContent())
			.map(PostResponse::of);
	}

	@GetMapping
	public Flux<PostResponse> findAllPost() {
		return postService.findAll()
			.map(PostResponse::of);
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<PostResponse>> findPost(@PathVariable("id") Long id) {
		return postService.findById(id)
			.map(p -> ResponseEntity.ok().body(PostResponse.of(p)))
			.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<?>> deletePost(@PathVariable Long id) {
		return postService.deleteById(id).then(Mono.just(ResponseEntity.noContent().build()));
	}
}
