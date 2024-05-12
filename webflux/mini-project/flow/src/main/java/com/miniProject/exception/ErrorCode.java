package com.miniProject.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
	QUEUE_ALREADY_REGISTERED_USER(HttpStatus.CONFLICT, "UQ-0001", "Already registered in queue");

	private final HttpStatus httpStatus;
	private final String code;
	private final String reason;

	public ApplicationException build() {
		return new ApplicationException(httpStatus, code, reason);
	}

	public ApplicationException build(Object ...args) {
		return new ApplicationException(httpStatus, code, reason.formatted(args));
	}
}
