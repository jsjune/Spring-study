package com.study.jpaMapping.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID") // 외래키
    private Member member;

    @OneToMany(mappedBy="order") // 양방향 // 연관관계 주인 order
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 양방향 매핑시 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자 // 양쪽으로 세팅이 들어감
    public void addOrderItem(OrderItem orderItem) { // 연관관계 편의 메소드 // 주문에서 주문아이템을 수정
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}
