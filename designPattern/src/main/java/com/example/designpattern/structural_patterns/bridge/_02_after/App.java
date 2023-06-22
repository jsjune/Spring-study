package com.example.designpattern.structural_patterns.bridge._02_after;

public class App {
    public static void main(String[] args) {
        Champion kda아리 = new 아리(new KDA());
        kda아리.skillE();

        Champion poolParty아리 = new 아리(new PoolParty());
        poolParty아리.skillE();

        Champion kda아칼리 = new 아칼리(new KDA());
        kda아칼리.skillE();
    }
}
