package barkingDog.스택의활용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/* 좋은 단어 */
public class Bj3986 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (stack.isEmpty()) {
                    stack.push(chars[j]);
                }else{
                    if (stack.peek() != chars[j]) {
                        stack.push(chars[j]);
                    } else {
                        stack.pop();
                    }
                }
            }
            if (stack.isEmpty()) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}
