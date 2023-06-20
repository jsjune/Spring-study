package com.example.desginpattern.singleton;

public class App {
    public static void main(String[] args) {
        Settings03 instance = Settings03.INSTANCE;
        Settings03 instance2 = Settings03.INSTANCE;
        System.out.println(instance==instance2);
        Setting instance1 = Setting.getInstance();
    }
}
