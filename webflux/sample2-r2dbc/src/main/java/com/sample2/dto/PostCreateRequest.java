package com.sample2.dto;

import lombok.Data;

@Data
public class PostCreateRequest {
	private Long userId;
	private String title;
	private String content;
}
