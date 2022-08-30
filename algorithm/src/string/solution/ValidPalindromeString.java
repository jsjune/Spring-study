package string.solution;

import java.util.Scanner;

public class ValidPalindromeString {
    public String solution(String str) {
        String answer = "NO";
        str = str.toUpperCase().replaceAll("[^A-Z]","");
        String tmp = new StringBuilder(str).reverse().toString();
        if(str.equals(tmp)) return answer = "YES";
        return answer;
    }

    public static void main(String[] args) {
        ValidPalindromeString t = new ValidPalindromeString();
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        System.out.println(t.solution(str));
    }
}
