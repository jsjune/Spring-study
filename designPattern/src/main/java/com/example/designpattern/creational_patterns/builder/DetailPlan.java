package com.example.designpattern.creational_patterns.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailPlan {
    private int day;
    private String plan;

    public DetailPlan(int day, String plan) {
        this.day = day;
        this.plan = plan;
    }
}
