package io.spring.springbatch.config.jdbc;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;

@Getter
public class Customer {
    @Id
    private Long id;
    private String name;
    private int age;

    @Builder
    public Customer(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
