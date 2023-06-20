package com.example.desginpattern.abstractFactory._02_after;

import lombok.*;

@Getter
@Setter
@ToString
public class Ship {
    private String name;
    private String color;
    private String logo;
    private Anchor anchor;
    private Wheel wheel;
}

