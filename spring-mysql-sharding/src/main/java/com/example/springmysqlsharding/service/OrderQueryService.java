package com.example.springmysqlsharding.service;

import com.example.springmysqlsharding.entity.Order;
import com.example.springmysqlsharding.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderQueryService {
    private final OrderRepository orderRepository;

    @Transactional // ✅ 새 트랜잭션 강제 생성
    public void saveOrderInTransaction(Order order, long id) {
        order.setId(id);
        orderRepository.save(order);
        log.info("Saved order {} in correct shard", id);
    }

    @Transactional(readOnly = true)
    public Optional<Order> getOrder(long orderId) {
        return orderRepository.findById(orderId);
    }
}
