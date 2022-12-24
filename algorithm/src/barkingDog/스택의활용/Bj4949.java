package barkingDog.스택의활용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/* 균형잡힌 세상 */
public class Bj4949 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (true) {
            str = br.readLine();
            if (str.equals(".")) {
                break;
            } else {
                solve(str);
            }
        }
        System.out.println(sb);

    }

    private static void solve(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    sb.append("no" + "\n");
                    return;
                } else {
                    stack.pop();
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    sb.append("no" + "\n");
                    return;
                } else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty()) {
            sb.append("yes" + "\n");
        } else {
            sb.append("no" + "\n");
        }
    }

}
