package com.example.desginpattern.factoryMethod._02_after;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.print(new WhiteshipFactory(), "whiteship", "abc@mail.com");
        client.print(new BlackshipFactory(), "blackship", "abc@mail.com");

//        client.print(Ship.builder().name("blackship").color("black").logo("⚓").build(), "blackship", "abc@mail.com");
//        Ship whiteship = new WhiteshipFactory().orderShip("whiteship", "keesun@mail.com");
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }
}
