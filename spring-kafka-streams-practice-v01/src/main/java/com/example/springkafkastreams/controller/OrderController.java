package com.example.springkafkastreams.controller;

import com.example.springkafkastreams.dto.OrderRequest;
import com.example.springkafkastreams.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        orderProducer.sendOrder(request.customerId(), request.amount());
        return ResponseEntity.ok("Order accepted");
    }
}

