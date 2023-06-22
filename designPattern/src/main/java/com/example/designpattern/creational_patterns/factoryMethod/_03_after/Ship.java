package com.example.designpattern.creational_patterns.factoryMethod._03_after;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ship implements ShipFactory {
    private String name;
    private String color;
    private String logo;

    @Builder
    public Ship(String name, String color, String logo) {
        this.name = name;
        this.color = color;
        this.logo = logo;
    }

    @Override
    public Ship createShip() {
        return this;
    }
}
