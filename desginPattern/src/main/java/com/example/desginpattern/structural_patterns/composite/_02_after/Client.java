package com.example.desginpattern.structural_patterns.composite._02_after;

public class Client {
    public static void main(String[] args) {
        Item doranBlade = new Item("도란검", 450);
        Item healPotion = new Item("체력 물약", 50);

        Bag bag = new Bag();
        bag.add(doranBlade);
        bag.add(healPotion);

        Character character = new Character(bag);

        Client client = new Client();
        client.printPrice(doranBlade);
        client.printPrice(bag);
        client.printPrice(character);
    }

    private void printPrice(Component component) {
        System.out.println(component.getPrice());
    }
}
