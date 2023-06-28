package com.example.designpattern.behavioral_patterns.strategy._02_after;


public class Client {
    public static void main(String[] args) {
        BlueLightRedLight game = new BlueLightRedLight();
        game.blueLight(new Normal());
        game.redLight(new Fastest());
        game.blueLight(new Speed() {
            @Override
            public void blueLight() {

            }

            @Override
            public void redLight() {

            }
        });
    }
}
