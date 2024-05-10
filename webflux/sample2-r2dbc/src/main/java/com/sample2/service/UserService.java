package com.sample2.service;

import java.time.Duration;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import com.sample2.domain.User;
import com.sample2.repository.UserR2dbcRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserR2dbcRepository userRepository;
	private final ReactiveRedisTemplate<String, User> reactiveRedisTemplate;

	public Mono<User> create(String name, String email) {
		return userRepository.save(
			User.builder()
				.name(name)
				.email(email)
				.build()
		);
	}

	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	public Mono<User> findById(Long id) {
		// return userRepository.findById(id);
		return reactiveRedisTemplate.opsForValue()
			.get(getUserCacheKey(id))
			.switchIfEmpty(userRepository.findById(id)
				.flatMap(u -> reactiveRedisTemplate.opsForValue()
					.set(getUserCacheKey(id), u, Duration.ofSeconds(30))
					.then(Mono.just(u))
				));
	}

	private static String getUserCacheKey(Long id) {
		return "users:%d".formatted(id);
	}

	public Mono<Void> deleteById(Long id) {
		return userRepository.deleteById(id)
			.then(reactiveRedisTemplate.unlink(getUserCacheKey(id)))
			.then(Mono.empty());
	}

	public Mono<Void> deleteByName(String name) {
		return userRepository.deleteByName(name);
	}

	public Mono<User> update(Long id, String name, String email) {
		return userRepository.findById(id)
			.flatMap(u -> {
				u.setName(name);
				u.setEmail(email);
				return userRepository.save(u);
			})
			.flatMap(u -> reactiveRedisTemplate.unlink(getUserCacheKey(id)).then(Mono.just(u)));
	}
}
