package string;

import java.util.Scanner;

public class NumberExtraction {
    public int solution(String str) {
        StringBuffer sb = new StringBuffer();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        NumberExtraction t = new NumberExtraction();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(t.solution(str));
    }
}
