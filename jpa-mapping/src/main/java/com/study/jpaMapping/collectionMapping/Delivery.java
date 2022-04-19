package com.study.jpaMapping.collectionMapping;

import com.study.jpaMapping.model2.DeliveryStatus;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    private DeliveryStatus status;
}
