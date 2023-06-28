package com.example.designpattern.behavioral_patterns.visitor._02_after;

public class Circle implements Shape{
    @Override
    public void accept(Device device) {
        device.print(this);
    }
}
