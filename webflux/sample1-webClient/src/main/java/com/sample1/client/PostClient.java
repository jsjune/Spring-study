package com.sample1.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PostClient {
	private final WebClient webClient;
	private final String url = "http://localhost:8090";

	public Mono<PostResponse> getPost(Long id) {
		String uriString = UriComponentsBuilder.fromHttpUrl(url)
			.path("/posts/%d".formatted(id))
			.buildAndExpand()
			.toUriString();
		return webClient.get()
			.uri(uriString)
			.retrieve()
			.bodyToMono(PostResponse.class);
	}


	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PostResponse {
		private String id;
		private String content;
	}

}
