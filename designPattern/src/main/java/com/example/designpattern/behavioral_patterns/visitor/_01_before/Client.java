package com.example.designpattern.behavioral_patterns.visitor._01_before;

public class Client {

    public static void main(String[] args) {
        Shape rectangle = new Rectangle();
        Device phone = new Phone();
        Device watch = new Watch();
        rectangle.printTo(phone);
        rectangle.printTo(watch);
    }
}
