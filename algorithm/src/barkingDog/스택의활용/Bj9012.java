package barkingDog.스택의활용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/* 괄호 */
public class Bj9012 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                if (stack.isEmpty()) {
                    stack.push(str.charAt(j));
                } else {
                    if (stack.peek() == '(' && str.charAt(j) == ')') {
                        stack.pop();
                    } else {
                        stack.push(str.charAt(j));
                    }
                }
            }
            if (stack.isEmpty()) {
                sb.append("YES" + "\n");
            } else {
                sb.append("NO" + "\n");
            }
        }
        System.out.println(sb);
    }

}
