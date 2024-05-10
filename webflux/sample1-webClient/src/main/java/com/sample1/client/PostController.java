package com.sample1.client;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	@GetMapping("/{id}")
	public Mono<PostClient.PostResponse> getPostContent(@PathVariable("id") Long id) {
		return postService.getPostContent(id);
	}

	@GetMapping("/search")
	public Flux<PostClient.PostResponse> getMultiplePostContent(@RequestParam(name = "ids") List<Long> idList) {
		// return postService.getMultiplePostContent(idList); // 순차O
		return postService.getParallelMultiplePostContent(idList); // 순차X
	}
}
