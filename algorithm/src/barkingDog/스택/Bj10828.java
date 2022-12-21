package barkingDog.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Bj10828 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            if (str[0].equals("push")) {
                stack.push(str[1]);
            } else if (str[0].equals("pop")) {
                sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
            } else if (str[0].equals("size")) {
                sb.append(stack.size()).append("\n");
            } else if (str[0].equals("empty")) {
                sb.append(stack.isEmpty() ? 1 : 0).append("\n");
            } else if (str[0].equals("top")) {
                sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}
