package com.example.redisprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisPracApplication.class, args);
    }

}
