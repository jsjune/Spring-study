package com.reactor;

public class Main {
	public static void main(String[] args) {
		Publisher publisher = new Publisher();

		publisher.startFlux()
			.subscribe(System.out::println);

		Operator1 operation = new Operator1();

	}
}
