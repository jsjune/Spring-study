package com.study.jpaMapping.collectionMapping;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders;

}
