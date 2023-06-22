package com.example.designpattern.creational_patterns.factoryMethod._02_after;

public class WhiteshipFactory implements ShipFactory{
    @Override
    public Ship createShip() {
        return new Whiteship();
    }
}
