package com.example.designpattern.behavioral_patterns.template._02_after_template_method;

public class Multiply extends FileProcessor{
    public Multiply(String path) {
        super(path);
    }

    @Override
    protected int getResult(int result, int number) {
        if (result == 0) {
            result = 1;
        }
        return result *= number;
    }

}
