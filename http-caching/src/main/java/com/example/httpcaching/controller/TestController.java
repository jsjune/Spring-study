package com.example.httpcaching.controller;

import com.example.httpcaching.entity.Order;
import com.example.httpcaching.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final OrderRepository orderRepository;
    private static String currentETag;

    @GetMapping("/data/{id}")
    public ResponseEntity<Order> getData(@PathVariable("id")String id, @RequestHeader(value = HttpHeaders.IF_NONE_MATCH, required = false) String ifNoneMatch) {
        if (ifNoneMatch != null && ifNoneMatch.equals(currentETag)) {
            System.out.println("데이터가 변경되지 않았습니다.");
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .eTag(currentETag)
                    .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))  // 60초 동안 캐싱 유지
                    .build();
        }
        System.out.println("데이터 요청");
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));
        currentETag = generateETag(order.getUpdatedAt());
        return ResponseEntity.ok()
                .eTag(currentETag)
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))  // 60초 동안 캐싱 유지
                .body(order);
    }

    @PutMapping("/data/{id}")
    public ResponseEntity<Void> updateData(@PathVariable("id") String id, @RequestBody Order order) {
        Order findOrder = orderRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다."));
        findOrder.setProductName(order.getProductName());
        findOrder.setQuantity(order.getQuantity());
        LocalDateTime now = LocalDateTime.now();
        findOrder.setUpdatedAt(now);
        orderRepository.save(findOrder);
        currentETag = generateETag(now);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/data")
    public ResponseEntity<Order> createData(@RequestBody Order req) {
        String id = UUID.randomUUID().toString();
        System.out.println("id = " + id);
        Order order = Order.builder()
            .id(id)
            .productName(req.getProductName())
            .quantity(req.getQuantity())
            .updatedAt(LocalDateTime.now())
            .build();
        order = orderRepository.save(order);
        return ResponseEntity.ok().body(order);
    }


    private static String generateETag(LocalDateTime now) {
        return "\"" + now + "\"";
    }
}

