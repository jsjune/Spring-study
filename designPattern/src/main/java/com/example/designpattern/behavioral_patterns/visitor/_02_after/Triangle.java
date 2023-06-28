package com.example.designpattern.behavioral_patterns.visitor._02_after;

public class Triangle implements Shape{
    @Override
    public void accept(Device device) {
        device.print(this);
    }
}
