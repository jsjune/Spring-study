package com.example.desginpattern.creational_patterns.factoryMethod._02_after;

public class BlackshipFactory implements ShipFactory{
    @Override
    public Ship createShip() {
        return new Blackship();
    }
}
