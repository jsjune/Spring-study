package StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CurriculumDesign {
    public String solution(String str1, String str2) {
        String answer = "NO";
        int cnt = 0;
        Queue<Character> queue = new LinkedList<>();
        for (char c : str2.toCharArray()) {
            queue.add(c);
        }
        for (char c : str1.toCharArray()) {
            for (int i = 0; i < queue.size(); i++) {
                if (c == queue.poll()) {
                    cnt++;
                    break;
                }
            }
        }
        if (!queue.isEmpty() && cnt == str1.length()) {
            answer = "YES";
        }
        return answer;
    }
    public static void main(String[] args) {
        CurriculumDesign t = new CurriculumDesign();
        Scanner kb = new Scanner(System.in);
        String str1 = kb.next();
        String str2 = kb.next();
        System.out.println(t.solution(str1,str2));
    }
}
