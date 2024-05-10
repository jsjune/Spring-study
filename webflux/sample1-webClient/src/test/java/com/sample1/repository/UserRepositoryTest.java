package com.sample1.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sample1.domain.User;

import reactor.test.StepVerifier;

class UserRepositoryTest {
	private final UserRepository userRepository = new UserRepositoryImpl();

	@Test
	void save() {
		User user = User.builder()
			.name("aaa")
			.email("aaa@naver.com")
			.build();
		StepVerifier.create(userRepository.save(user))
			.assertNext(u -> {
				assertEquals(1L, u.getId());
				assertEquals("aaa", u.getName());
			})
			.verifyComplete();
	}

	@Test
	void findAll() {
		userRepository.save(User.builder()
			.name("aaa")
			.email("aaa@naver.com")
			.build());
		userRepository.save(User.builder()
			.name("aaa2")
			.email("aaa2@naver.com")
			.build());
		userRepository.save(User.builder()
			.name("aaa3")
			.email("aaa3@naver.com")
			.build());

		StepVerifier.create(userRepository.findAll())
			.expectNextCount(3)
			.verifyComplete();
	}

	@Test
	void findById() {
		userRepository.save(User.builder()
			.name("aaa")
			.email("aaa@naver.com")
			.build());
		userRepository.save(User.builder()
			.name("aaa2")
			.email("aaa2@naver.com")
			.build());

		StepVerifier.create(userRepository.findById(2L))
			.assertNext(u -> {
				assertEquals(2L, u.getId());
				assertEquals("aaa2", u.getName());
			}).verifyComplete();
	}

	@Test
	void deleteById() {
		userRepository.save(User.builder()
			.name("aaa")
			.email("aaa@naver.com")
			.build());
		userRepository.save(User.builder()
			.name("aaa2")
			.email("aaa2@naver.com")
			.build());

		StepVerifier.create(userRepository.deleteById(2L))
			.expectNextCount(1)
			.verifyComplete();
	}
}
