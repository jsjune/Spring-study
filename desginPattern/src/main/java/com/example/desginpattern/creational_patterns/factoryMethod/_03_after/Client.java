package com.example.desginpattern.creational_patterns.factoryMethod._03_after;


public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.print(Ship.builder()
                .name("whiteship")
                .color("white")
                .logo("\uD83D\uDEE5")
                .build(),
                "whiteship",
                "abc@mail.com"
        );
        client.print(Ship.builder()
                .name("blackship")
                .color("black")
                .logo("⚓")
                .build(),
                "blackship",
                "abc@mail.com"
        );
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }
}
