package com.example.designpattern.behavioral_patterns.template._02_after_template_method;

public class Client {
    public static void main(String[] args) {
        Plus plus = new Plus("number.txt");
        int result = plus.process();
        Multiply multiply = new Multiply("number.txt");
        int result2 = multiply.process();
        System.out.println(result);
        System.out.println(result2);
    }
}
