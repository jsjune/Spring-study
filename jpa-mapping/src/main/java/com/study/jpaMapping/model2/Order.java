package com.study.jpaMapping.model2;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToOne
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    private Date orderDate;

}
