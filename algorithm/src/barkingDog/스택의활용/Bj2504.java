package barkingDog.스택의활용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/* 괄호의 값 */
public class Bj2504 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int result = 0;
        int value = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(str.charAt(i));
                value *= 2;
            }else if(str.charAt(i) == '[') {
                stack.push(str.charAt(i));
                value *= 3;
            } else if (str.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    result=0;
                    break;
                } else if (str.charAt(i - 1) == '(') {
                    result+=value;
                }
                stack.pop();
                value /= 2;
            } else if (str.charAt(i) == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    result=0;
                    break;
                } else if (str.charAt(i - 1) == '[') {
                    result += value;
                }
                stack.pop();
                value /= 3;
            }
        }
        System.out.println(result);
    }

}
