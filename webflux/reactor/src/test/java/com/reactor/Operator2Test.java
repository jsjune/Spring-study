package com.reactor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

class Operator2Test {
	private Operator2 operator = new Operator2();

	@Test

	void fluxConcatMap() {
		StepVerifier.create(operator.fluxConcatMap())
			.expectNextCount(100)
			.verifyComplete();
	}

	@Test
	void monoFlatMapMany() {
		StepVerifier.create(operator.monoFlatMapMany())
			.expectNextCount(10)
			.verifyComplete();
	}

	@Test
	void defaultIfEmpty1() {
		StepVerifier.create(operator.defaultIfEmpty1())
			.expectNext(30)
			.verifyComplete();
	}

	@Test
	void switchIfEmpty1() {
		StepVerifier.create(operator.switchIfEmpty1())
			.expectNext(60)
			.verifyComplete();
	}

	@Test
	void switchIfEmpty2() {
		StepVerifier.create(operator.switchIfEmpty2())
			.expectError()
			.verify();
	}

	@Test
	void fluxMerge() {
		StepVerifier.create(operator.fluxMerge())
			.expectNext("1","2","3","4")
			.verifyComplete();
	}

	@Test
	void monoMerge() {
		StepVerifier.create(operator.monoMerge())
			.expectNext("1","2","3")
			.verifyComplete();
	}

	@Test
	void fluxZip() {
		StepVerifier.create(operator.fluxZip())
			.expectNext("ad","be","cf")
			.verifyComplete();
	}

	@Test
	void monoZip() {
		StepVerifier.create(operator.monoZip())
			.expectNext(6)
			.verifyComplete();
	}
}
