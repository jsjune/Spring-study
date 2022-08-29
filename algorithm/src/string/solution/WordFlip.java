package string.solution;

import java.util.ArrayList;
import java.util.Scanner;

public class WordFlip {
    public ArrayList<String> solution(int n, String[] strings) {
        ArrayList<String> answer = new ArrayList<>();
        for (String x : strings) {

//            String tmp = new StringBuilder(x).reverse().toString();
            char[] s = x.toCharArray();
            int lt=0, rt=x.length()-1;
            while (lt < rt) {
                char tmp = s[lt];
                s[lt] = s[rt];
                s[rt] = tmp;
                lt++;
                rt--;
            }
            String tmp = String.valueOf(s);

            answer.add(tmp);
        }
        return answer;
    }

    public static void main(String[] args) {
        WordFlip t = new WordFlip();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = kb.next();
        }
        for (String x : t.solution(n, str)) {
            System.out.println(x);
        }
    }
}

