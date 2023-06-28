package com.example.designpattern.behavioral_patterns.template._03_after_template_callback;

public class Client {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor("number.txt");
        int plus = fileProcessor.process(((result, number) -> result += number));
        System.out.println(plus);
    }
}
