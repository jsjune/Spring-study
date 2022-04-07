package com.study.jpaMapping.joinmodel;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 구현 클래스마다 테이블 전략 // 추상클래스 Item 으로 해야된다 // 사용XX
@DiscriminatorColumn // DTYPE 부모 클래스에 선언한다. // 단일 테이블시 생략
//@DiscriminatorColumn // 하위 클래스에 선언한다.
public abstract class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
