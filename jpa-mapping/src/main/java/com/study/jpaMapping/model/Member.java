package com.study.jpaMapping.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy="member") // 양방향 // 연관관계 주인 member // 안쓰는게 좋음 따로 조회하는게 좋음
    private List<Order> orders = new ArrayList<>();
}
