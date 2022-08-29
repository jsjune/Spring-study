package string.solution;

import java.util.Scanner;

public class UpperAndLowerCaseTransform {
    public String solution(String str) {
        String answer = "";
        for (char x : str.toCharArray()) {
            if (Character.isLowerCase(x)) answer += Character.toUpperCase(x);
            else answer += Character.toLowerCase(x);
        }
        return answer;
    }

    public static void main(String[] args) {
        UpperAndLowerCaseTransform t = new UpperAndLowerCaseTransform();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(t.solution(str));
    }
}
