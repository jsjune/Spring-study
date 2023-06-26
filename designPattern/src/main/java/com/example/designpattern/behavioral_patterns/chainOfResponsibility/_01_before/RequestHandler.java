package com.example.designpattern.behavioral_patterns.chainOfResponsibility._01_before;

public class RequestHandler {

    public void handler(Request request) {
        System.out.println(request.getBody());
    }
}
