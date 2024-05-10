package com.sample1.client;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostClient postClient;

	public Mono<PostClient.PostResponse> getPostContent(Long id) {
		return postClient.getPost(id)
			.onErrorResume(
				err -> Mono.just(new PostClient.PostResponse(id.toString(), "Fallback data %d".formatted(id))));
	}

	public Flux<PostClient.PostResponse> getMultiplePostContent(List<Long> idList) {
		return Flux.fromIterable(idList)
			.flatMap(this::getPostContent)
			.log();
	}

	public Flux<PostClient.PostResponse> getParallelMultiplePostContent(List<Long> idList) {
		return Flux.fromIterable(idList)
			.parallel()
			.runOn(Schedulers.parallel())
			.flatMap(this::getPostContent)
			.log()
			.sequential();
	}
}
