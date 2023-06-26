package com.example.designpattern.behavioral_patterns.interpreter;

import java.util.Stack;

import static com.example.designpattern.behavioral_patterns.interpreter.PostfixExpression.minus;
import static com.example.designpattern.behavioral_patterns.interpreter.PostfixExpression.plus;

public class PostfixParser {

    public static PostfixExpression parse(String expression) {
        Stack<PostfixExpression> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            stack.push(getExpression(c, stack));
        }
        return stack.pop();
    }

    private static PostfixExpression getExpression(char c, Stack<PostfixExpression> stack) {
        switch (c) {
            case '+':
                return plus(stack.pop(), stack.pop());
//                return new PlusExpression(stack.pop(), stack.pop());
            case '-':
                PostfixExpression right = stack.pop();
                PostfixExpression left = stack.pop();
                return minus(left, right);
//                return new MinusExpression(left, right);
            default:
                return new VariableExpression(c);
        }
    }
}
