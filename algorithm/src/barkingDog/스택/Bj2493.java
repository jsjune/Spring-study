package barkingDog.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Top {

    int height;
    int num;

    public Top(int height, int num) {
        this.height = height;
        this.num = num;
    }
}

public class Bj2493 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Top> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int height = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) {
                result.append("0 ");
                stack.push(new Top(height, i));
            } else {
                while (true) {
                    if (stack.isEmpty()) {
                        result.append("0 ");
                        stack.push(new Top(height, i));
                        break;
                    }
                    Top top = stack.peek();
                    if (top.height > height) {
                        result.append(top.num + " ");
                        stack.push(new Top(height, i));
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
        }
        System.out.println(result);

    }
}
