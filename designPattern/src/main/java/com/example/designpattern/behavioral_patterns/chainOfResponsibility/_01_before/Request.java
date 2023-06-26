package com.example.designpattern.behavioral_patterns.chainOfResponsibility._01_before;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {

    private String body;

    public Request(String body) {
        this.body = body;
    }

}
