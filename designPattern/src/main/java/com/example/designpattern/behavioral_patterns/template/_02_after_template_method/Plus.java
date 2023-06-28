package com.example.designpattern.behavioral_patterns.template._02_after_template_method;

public class Plus extends FileProcessor{
    public Plus(String path) {
        super(path);
    }

    @Override
    protected int getResult(int result, int number) {
        return result += number;
    }
}
