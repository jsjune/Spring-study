package com.example.desginpattern.singleton;

public class Settings01 {
    private static volatile Settings01 instance;

    private Settings01() {
    }

    public static Settings01 getInstance() {
        if (instance == null) {
            synchronized (Settings01.class) {
                if (instance == null) {
                    instance = new Settings01();
                }
            }
        }
        return instance;
    }
}
