package com.example.designpattern.creational_patterns.factoryMethod._01_before;


public class Client {

    public static void main(String[] args) {
        Ship whiteship = ShipFactory.orderShip("Whiteship", "abc@mail.com");
        System.out.println(whiteship);

        Ship blackship = ShipFactory.orderShip("Blackship", "abc@mail.com");
        System.out.println(blackship);
    }

}
