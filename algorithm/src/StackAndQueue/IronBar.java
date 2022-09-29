package StackAndQueue;

import java.util.Scanner;
import java.util.Stack;

public class IronBar {
    public int solution(String str) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)=='(') stack.push('(');
            else{
                stack.pop();
                if(str.charAt(i-1)=='(') answer += stack.size();
                else answer++;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        IronBar t = new IronBar();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(t.solution(str));
    }
}


