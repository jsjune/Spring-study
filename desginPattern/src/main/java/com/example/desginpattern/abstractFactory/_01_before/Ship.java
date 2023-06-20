package com.example.desginpattern.abstractFactory._01_before;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Ship {
    private String name;
    private String color;
    private String logo;
    private Anchor anchor;
    private Wheel wheel;
}

