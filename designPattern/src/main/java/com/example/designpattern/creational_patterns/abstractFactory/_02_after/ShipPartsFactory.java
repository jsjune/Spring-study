package com.example.designpattern.creational_patterns.abstractFactory._02_after;

public interface ShipPartsFactory {
    Anchor createAnchor();
    Wheel createWheel();
}
