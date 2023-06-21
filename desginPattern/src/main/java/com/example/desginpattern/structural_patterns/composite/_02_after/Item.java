package com.example.desginpattern.structural_patterns.composite._02_after;

public class Item implements Component{
    private String naem;
    private int price;

    public Item(String naem, int price) {
        this.naem = naem;
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
