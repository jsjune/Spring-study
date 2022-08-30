package string;

import java.util.Scanner;

public class ValidPalindromeString {
    public String solution(String str) {
        char[] s = str.toLowerCase().toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isAlphabetic(s[i])) {
                sb.append(s[i]);
            }
        }
        String answer = sb.toString();
        String tmp = new StringBuilder(sb).reverse().toString();
        if (answer.equals(tmp)) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static void main(String[] args) {
        ValidPalindromeString t = new ValidPalindromeString();
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.println(t.solution(str));
    }
}
