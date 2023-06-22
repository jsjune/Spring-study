package com.example.designpattern.structural_patterns.proxy._01_before;

public class Client {
    public static void main(String[] args) {
        GameService gameService = new GameService();
        gameService.startGame();
    }
}
