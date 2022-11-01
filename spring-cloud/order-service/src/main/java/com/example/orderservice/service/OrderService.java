package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderByOrderId(String orderId);

    Iterable<Order> getOrdersByUserId(String userId);
}
