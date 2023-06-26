package com.example.designpattern.behavioral_patterns.chainOfResponsibility._02_after;

public class LoggingRequestHandler extends RequestHandler{

    public LoggingRequestHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(Request request) {
        System.out.println("로깅");
        super.handle(request);
    }
}
