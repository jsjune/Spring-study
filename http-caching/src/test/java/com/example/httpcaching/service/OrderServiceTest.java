package com.example.httpcaching.service;

import com.example.httpcaching.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void saveTest() {
        // given
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setProductName("상품" + i);
            order.setQuantity(i + 100);
            orderService.saveOrder(order);

        }
    }

    @Test
    public void selectTest() {
        long id3 = 682051022746554368L;
        Optional<Order> order3 = orderService.getOrderById(id3);
        order3.ifPresent(System.out::println);

        long id2 = 682051022578782208L;
        Optional<Order> order2 = orderService.getOrderById(id2);
        order2.ifPresent(System.out::println);

        long id1 = 682051022939492352L;
        Optional<Order> order1 = orderService.getOrderById(id1);
        order1.ifPresent(System.out::println);
    }
}
