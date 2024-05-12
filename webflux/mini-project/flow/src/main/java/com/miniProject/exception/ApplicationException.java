package com.miniProject.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicationException extends RuntimeException{
	private HttpStatus httpStatus;
	private String code;
	private String reason;
}
