package com.reactor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

class Scheduler1Test {
	private Scheduler1 scheduler = new Scheduler1();

	@Test
	void fluxMapWithSubscribeOn() {
		StepVerifier.create(scheduler.fluxMapWithSubscribeOn())
			.expectNext(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
			.verifyComplete();
	}

	@Test
	void fluxMapWithPublishOn() {
		StepVerifier.create(scheduler.fluxMapWithPublishOn())
			.expectNextCount(10)
			.verifyComplete();
	}
}
