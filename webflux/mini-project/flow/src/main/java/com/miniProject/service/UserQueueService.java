package com.miniProject.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.miniProject.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQueueService {
	private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
	private final String USER_QUEUE_WAIT_KEY = "users:queue:%s:wait";
	private final String USER_QUEUE_PROCEED_KEY = "users:queue:%s:proceed";
	private final String USER_QUEUE_WAIT_KEY_FOR_SCAN = "users:queue:*:wait";

	@Value("${scheduler.enabled}")
	private Boolean scheduling = false;

	public Mono<Long> registerWaitQueue(String queue, Long userId) {
		long unixTimestamp = Instant.now().getEpochSecond();
		return reactiveRedisTemplate.opsForZSet()
			.add(USER_QUEUE_WAIT_KEY.formatted(queue), userId.toString(), unixTimestamp)
			.filter(i -> i)
			.switchIfEmpty(Mono.error(ErrorCode.QUEUE_ALREADY_REGISTERED_USER.build()))
			.flatMap(
				i -> reactiveRedisTemplate.opsForZSet().rank(USER_QUEUE_WAIT_KEY.formatted(queue), userId.toString()))
			.map(i -> i >= 0 ? i + 1 : i);
	}

	public Mono<Long> allowUser(String queue, Long count) {
		// 진입을 허용하는 단계
		// 1. wait queue 사용자를 제거
		// 2. proceed queue 사용자를 추가
		return reactiveRedisTemplate.opsForZSet().popMin(USER_QUEUE_WAIT_KEY.formatted(queue), count)
			.flatMap(member -> reactiveRedisTemplate.opsForZSet()
				.add(USER_QUEUE_PROCEED_KEY.formatted(queue), member.getValue(), Instant.now().getEpochSecond()))
			.count();
	}

	public Mono<Boolean> isAllowed(String queue, Long userId) {
		return reactiveRedisTemplate.opsForZSet().rank(USER_QUEUE_PROCEED_KEY.formatted(queue), userId.toString())
			.defaultIfEmpty(-1L)
			.map(rank -> rank >= 0);
	}

	public Mono<Boolean> isAllowedByToken(String queue, Long userId, String token) {
		return this.generateToken(queue, userId)
			.filter(gen -> gen.equalsIgnoreCase(token))
			.map(i -> true)
			.defaultIfEmpty(false);
	}

	public Mono<Long> getRank(String queue, Long userId) {
		return reactiveRedisTemplate.opsForZSet().rank(USER_QUEUE_WAIT_KEY.formatted(queue), userId.toString())
			.defaultIfEmpty(-1L)
			.map(rank -> rank >= 0 ? rank + 1 : rank);
	}

	public Mono<String> generateToken(String queue, Long userId) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			String input = "user-queue-%s-%d".formatted(queue, userId);
			byte[] encodeHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

			StringBuilder hexString = new StringBuilder();
			for (byte aByte : encodeHash) {
				hexString.append(String.format("%02x", aByte));
			}
			return Mono.just(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Scheduled(initialDelay = 5000, fixedDelay = 10000)
	public void scheduleAllowUser() {
		if (!scheduling) {
			log.info("passed scheduling...");
			return;
		}
		log.info("Called scheduling...");

		Long maxAllowUserCount = 100L;
		reactiveRedisTemplate.scan(ScanOptions.scanOptions()
				.match(USER_QUEUE_WAIT_KEY_FOR_SCAN)
				.count(100)
				.build())
			.map(key -> key.split(":")[2])
			.flatMap(queue -> allowUser(queue, maxAllowUserCount).map(allowed -> Tuples.of(queue, allowed)))
			.doOnNext(tuple -> log.info(
				"Tried %d and allowed %d members of %s queue".formatted(maxAllowUserCount, tuple.getT2(),
					tuple.getT1())))
			.subscribe();
	}
}
