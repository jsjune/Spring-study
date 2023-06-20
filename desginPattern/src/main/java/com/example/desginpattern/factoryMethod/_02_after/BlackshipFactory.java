package com.example.desginpattern.factoryMethod._02_after;

public class BlackshipFactory implements ShipFactory{
    @Override
    public Ship createShip() {
        return new Blackship();
    }
}
