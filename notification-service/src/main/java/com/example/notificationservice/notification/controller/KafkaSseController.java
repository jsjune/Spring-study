package com.example.notificationservice.notification.controller;

import com.example.notificationservice.notification.service.kafka.KafkaSseService;
import com.example.notificationservice.notification.service.kafka.RedisAndKafkaSseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
@RequiredArgsConstructor
public class KafkaSseController {

    private final RedisAndKafkaSseService sseService;
    private final KafkaSseService kafkaSseService;

    @GetMapping("/{clientId}")
    public SseEmitter stream(@PathVariable("clientId") String clientId) {
        return sseService.addEmitter(clientId);
    }

    @GetMapping("/test/{clientId}")
    public String message(@PathVariable("clientId") String clientId, @RequestParam("message") String message) {
        return kafkaSseService.producer(clientId, message);
    }
}
