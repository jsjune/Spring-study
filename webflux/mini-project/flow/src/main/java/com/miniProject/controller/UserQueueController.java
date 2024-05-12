package com.miniProject.controller;

import java.time.Duration;

import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.miniProject.dto.AllowUserResponse;
import com.miniProject.dto.AllowedUserResponse;
import com.miniProject.dto.RankNumberResponse;
import com.miniProject.dto.RegisterUserResponse;
import com.miniProject.service.UserQueueService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/queue")
@RequiredArgsConstructor
public class UserQueueController {
	private final UserQueueService userQueueService;

	// 대기열 등록 API
	@PostMapping
	public Mono<RegisterUserResponse> registerUser(@RequestParam(name = "queue", defaultValue = "default") String queue,
		@RequestParam(name = "user_id") Long userId) {
		return userQueueService.registerWaitQueue(queue, userId)
			.map(RegisterUserResponse::new);
	}

	// 진입 허용 API
	@PostMapping("/allow")
	public Mono<AllowUserResponse> allowUser(@RequestParam(name = "queue", defaultValue = "default") String queue,
		@RequestParam(name = "count") Long count) {
		return userQueueService.allowUser(queue, count)
			.map(allowed -> new AllowUserResponse(count, allowed));
	}

	// 진입이 가능한 상태인지 조회
	@GetMapping("/allowed")
	public Mono<AllowedUserResponse> isAllowedUser(@RequestParam(name = "queue", defaultValue = "default") String queue,
		@RequestParam(name = "user_id") Long userId,
		@RequestParam(name = "token") String token) {
		// return userQueueService.isAllowed(queue, userId)
		// 	.map(AllowedUserResponse::new);
		return userQueueService.isAllowedByToken(queue, userId, token)
			.map(AllowedUserResponse::new);
	}

	@GetMapping("/rank")
	public Mono<RankNumberResponse> getRankUser(@RequestParam(name = "queue", defaultValue = "default") String queue,
		@RequestParam(name = "user_id") Long userId) {
		return userQueueService.getRank(queue, userId)
			.map(RankNumberResponse::new);
	}

	@GetMapping("/touch")
	public Mono<?> touch(@RequestParam(name = "queue", defaultValue = "default") String queue,
		@RequestParam(name = "user_id") Long userId,
		ServerWebExchange exchange) {
		return Mono.defer(() -> userQueueService.generateToken(queue, userId))
			.map(token -> {
				exchange.getResponse().addCookie(
					ResponseCookie
						.from("user-queue-%s-token".formatted(queue), token)
						.maxAge(Duration.ofSeconds(300))
						.path("/")
						.build()
				);
				return token;
			});
	}
}
