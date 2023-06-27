package com.example.designpattern.behavioral_patterns.observer._02_after;

public class Client {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("jsj");
        User user2 = new User("jdh");

        chatServer.register("웹소켓", user1);
        chatServer.register("웹소켓", user2);

        chatServer.register("디자인패턴", user1);

        chatServer.sendMessage(user1,"웹소켓","아.. 어렵다");
        chatServer.sendMessage(user2, "디자인패턴", "옵저버 패턴으로 만든 채팅");

        chatServer.unregister("디자인패턴", user2);
        chatServer.sendMessage(user2, "디자인패턴","옵저버 패턴 장, 단점 보는 중");

    }
}
