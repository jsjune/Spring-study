package com.example.designpattern.behavioral_patterns.iterator._02_after;


import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        Board board = new Board();
        board.addPost("3. 디자인 패턴 게임");
        board.addPost("2. 선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
        board.addPost("1. 지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

        Iterator<Post> iterator = board.getPosts().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }

        Iterator<Post> recentPostIterator = board.getRecentPostIterator();
        while (recentPostIterator.hasNext()) {
            System.out.println(recentPostIterator.next().getTitle());
        }
    }
}
