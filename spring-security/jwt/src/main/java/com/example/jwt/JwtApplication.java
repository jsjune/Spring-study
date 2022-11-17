package com.example.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(JwtApplication.class, args);

        StringRedisTemplate template = run.getBean(StringRedisTemplate.class);
        String val = template.opsForValue().get("testkey1");
        System.out.println(val);

        val = template.opsForValue().get("testkey2");
        System.out.println(val);
    }

}
