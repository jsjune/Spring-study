package com.example.lock.pessimisticLock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Home {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private int price;

    public Home(String name, String address, int price) {
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public int decreasePrice(int price) throws IllegalAccessException {
        if (this.price - price < 0) {
            throw new IllegalAccessException("가격이 부족해");
        }
        return this.price -= price;
    }
}
