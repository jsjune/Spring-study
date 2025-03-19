package com.example.springelk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@Slf4j
public class SpringELKApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringELKApplication.class, args);
    }

    @GetMapping("/hello")
    public Flux<List<Map<String, Object>>> hello(@RequestParam String a, @RequestParam String b) {
        log.info("a: {}, b: {}", a, b);
        List<Map<String, Object>> res = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("a", a + "_" + i);
            map.put("b", b + "_" + i);
            res.add(map);
        }
        return Flux.just(res);
    }

    @GetMapping("/hello2")
    public Mono<List<String>> hello2(@RequestParam String a, @RequestParam String b) {
        log.info("a: {}, b: {}", a, b);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            res.add(a + "_" + i + " " + b + "_" + i);
        }
        return Mono.just(res);
    }
}
