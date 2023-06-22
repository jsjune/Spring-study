package com.example.designpattern.structural_patterns.composite._01_before;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Bag {
    private List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }
}
