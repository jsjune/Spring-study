package com.study.jpaMapping.joinmodel2;

import javax.persistence.*;

@Entity
public class OrderItem extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    private int orderPrice;
    private int count;
}
