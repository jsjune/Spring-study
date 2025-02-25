package com.example.springmysqlsharding.controller;

import com.example.springmysqlsharding.entity.Order;
import com.example.springmysqlsharding.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return "Order saved successfully!";
    }
}
