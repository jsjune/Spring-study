package com.example.designpattern.behavioral_patterns.strategy._02_after;

public class BlueLightRedLight {
    public void blueLight(Speed speed) {
        speed.blueLight();
    }

    public void redLight(Speed speed) {
        speed.redLight();
    }
}
