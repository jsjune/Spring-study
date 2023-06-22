package com.example.designpattern.creational_patterns.factoryMethod._02_after;

public class BlackshipFactory implements ShipFactory{
    @Override
    public Ship createShip() {
        return new Blackship();
    }
}
