package com.reactor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

class Operator4Test {
	private Operator4 operator = new Operator4();

	@Test
	void fluxDelayAndLimit() {
		StepVerifier.create(operator.fluxDelayAndLimit())
			.expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
			.verifyComplete();
	}

	@Test
	void fluxSample() {
		StepVerifier.create(operator.fluxSample())
			// .expectNextCount(100)
			.verifyComplete();
	}
}
