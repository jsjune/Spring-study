package StackAndQueue;

import java.util.Scanner;
import java.util.Stack;

public class PostfixOperation {
    public int solution(String str) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) stack.push(c - 48);
            else {
                int rt = stack.pop();
                int lt = stack.pop();
                if (c == '+') stack.push(lt + rt);
                else if (c == '-') stack.push(lt - rt);
                else if (c == '*') stack.push(lt * rt);
                else if (c == '/') stack.push(lt / rt);
            }
        }
        answer = stack.get(0);
        return answer;
    }

    public static void main(String[] args) {
        PostfixOperation t = new PostfixOperation();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(t.solution(str));
    }
}
