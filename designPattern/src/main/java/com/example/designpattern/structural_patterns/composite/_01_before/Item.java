package com.example.designpattern.structural_patterns.composite._01_before;

import lombok.Getter;

@Getter
public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
