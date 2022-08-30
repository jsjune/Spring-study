package string;

import java.util.Scanner;

public class PalindromeString {

    public String solution(String str) {
        String tmp = new StringBuilder(str).reverse().toString();
        if (str.equalsIgnoreCase(tmp.toLowerCase())) {
            return "YES";
        } else {
            return "NO";
        }
//        char[] s = str.toCharArray();
//        int lt = 0, rt = s.length - 1;
//        while (lt < rt) {
//            char tmp = s[lt];
//            s[lt] = s[rt];
//            s[rt] = tmp;
//            lt++;
//            rt--;
//        }
//        String tmp = String.valueOf(s);
//        if (str.toLowerCase().equals(tmp.toLowerCase())) {
//            return "YES";
//        } else {
//            return "NO";
//        }
    }

    public static void main(String[] args) {
        PalindromeString t = new PalindromeString();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(t.solution(str));
    }
}
