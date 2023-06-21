package com.example.desginpattern.creational_patterns.singleton;

public class Settings02 {
    private Settings02() {
    }

    private static class SettingsHolder {
        private static final Settings02 INSTANCE = new Settings02();
    }

    private static Settings02 getInstance() {
        return SettingsHolder.INSTANCE;
    }
}
