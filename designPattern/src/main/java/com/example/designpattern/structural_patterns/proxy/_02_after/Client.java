package com.example.designpattern.structural_patterns.proxy._02_after;

public class Client {
    public static void main(String[] args) {
        GameService gameService = new GameServiceProxy();
        gameService.startGame();
    }
}
