package com.example.desginpattern.creational_patterns.abstractFactory._02_after;

public class ShipInventory {
    public static void main(String[] args) {
        ShipFactory shipFactory = new WhiteshipFactory(new WhiteshipPartsFactory());
        Ship ship = shipFactory.createShip();
        System.out.println(ship.getAnchor().getClass());
        System.out.println(ship.getWheel().getClass());

        ShipFactory shipProFactory = new WhiteshipFactory(new WhiteshipPartsProFactory());
        Ship proShip = shipProFactory.createShip();
        System.out.println(proShip.getAnchor().getClass());
        System.out.println(proShip.getWheel().getClass());
    }
}
