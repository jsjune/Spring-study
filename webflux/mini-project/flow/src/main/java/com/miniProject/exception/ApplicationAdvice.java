package com.miniProject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class ApplicationAdvice {

	@ExceptionHandler(ApplicationException.class)
	Mono<ResponseEntity<ServerExceptionRes>> applicationExceptionHandler(ApplicationException ex) {
		return Mono.just(ResponseEntity
			.status(ex.getHttpStatus())
			.body(new ServerExceptionRes(ex.getCode(), ex.getReason()))
		);
	}

	public record ServerExceptionRes(String code, String reason) {
	}

}
