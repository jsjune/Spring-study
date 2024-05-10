package com.mvc;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class MvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}

	@GetMapping("/posts/{id}")
	public Map<String, String> getPosts(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		log.info("Request URL: {}", request.getRequestURL());

		Thread.sleep(300);
		if (id > 10L) {
			throw new Exception("Too long");
		}
		return Map.of("id", id.toString(), "content", "Post content is %d".formatted(id));
	}
}
