package com.example.desginpattern.creational_patterns.factoryMethod._02_after;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ship{
    private String name;
    private String color;
    private String logo;

    @Builder
    public Ship(String name, String color, String logo) {
        this.name = name;
        this.color = color;
        this.logo = logo;
    }

}
