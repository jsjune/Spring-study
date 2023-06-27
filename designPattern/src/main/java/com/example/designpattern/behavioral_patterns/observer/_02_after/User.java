package com.example.designpattern.behavioral_patterns.observer._02_after;

import lombok.Getter;

@Getter
public class User implements Subscriber{

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void handleMessage(String message) {
        System.out.println("message = " + message);
    }
}
