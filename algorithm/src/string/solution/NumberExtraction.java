package string.solution;

import java.util.Scanner;

public class NumberExtraction {
    public int solution(String str) {
        int answer = 0;
        for (char x : str.toCharArray()) {
            if(x>=48&&x<=57) answer = answer * 10 + (x - 48);
        }
        return answer;
    }

    public static void main(String[] args) {
        NumberExtraction t = new NumberExtraction();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        System.out.println(t.solution(str));
    }
}
