package com.example.desginpattern.structural_patterns.bridge._01_before;

public class KDA아리 implements Champion{
    @Override
    public void move() {
        System.out.println("KDA아리 move");
    }

    @Override
    public void skillQ() {
        System.out.println("KDA 아리 Q");
    }

    @Override
    public void skillW() {
        System.out.println("KDA 아리 W");
    }

    @Override
    public void skillE() {
        System.out.println("KDA 아리 E");
    }

    @Override
    public void skillR() {
        System.out.println("KDA 아리 R");
    }
}
