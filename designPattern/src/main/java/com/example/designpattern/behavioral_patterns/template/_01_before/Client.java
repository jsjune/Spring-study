package com.example.designpattern.behavioral_patterns.template._01_before;

public class Client {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor("number.txt");
        int result = fileProcessor.process();
        MultiplyFileProcessor multiplyFileProcessor = new MultiplyFileProcessor("number.txt");
        int result2 = multiplyFileProcessor.process();
        System.out.println(result);
        System.out.println(result2);
    }

}
