package com.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Main {
	public static void main(String[] args) {
		Publisher publisher = new Publisher();

//		publisher.startFlux()
//			.subscribe(System.out::println);

//		Operator1 operation = new Operator1();
//		operation.fluxFilter()
//			.subscribe(System.out::println);

//		Scheduler1 scheduler1 = new Scheduler1();
//		scheduler1.fluxMapWithPublishOn()
//			.subscribe(System.out::println);
//
//		System.out.println("=========================================");
//
//		scheduler1.fluxMapWithSubscribeOn()
//			.subscribe(System.out::println);

//		Flux.range(1, 5) // 1부터 5까지의 숫자 생성
//			.doOnNext(i -> System.out.println("Generating item " + i + " on thread: " + Thread.currentThread().getName()))
//			.subscribeOn(Schedulers.boundedElastic()) // 데이터 생성과 발행 스레드 설정
//			.doOnNext(i -> System.out.println("After subscribeOn - item " + i + " on thread: " + Thread.currentThread().getName()))
//			.publishOn(Schedulers.parallel()) // 데이터 처리 스레드 설정
//			.map(i -> i * 10) // 데이터 변환 작업
//			.doOnNext(i -> System.out.println("After publishOn - transformed item " + i + " on thread: " + Thread.currentThread().getName()))
//			.subscribe(i -> System.out.println("Consuming item " + i + " on thread: " + Thread.currentThread().getName()));
//
//		// 프로그램이 종료되지 않도록 잠시 대기
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		Flux.range(1, 3)
			.map(i -> {
				System.out.println("Before publishOn - Item " + i + " processed on thread: " + Thread.currentThread().getName());
				return i + 5;
			})
			.publishOn(Schedulers.parallel())  // 이후 작업은 parallel 스레드 풀에서 처리
			.map(i -> {
				System.out.println("After publishOn - Item " + i + " processed on thread: " + Thread.currentThread().getName());
				return i * 10;
			})
			.subscribe(result -> System.out.println("Result: " + result + " processed on thread: " + Thread.currentThread().getName()));

		// 프로그램이 종료되지 않도록 잠시 대기
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
