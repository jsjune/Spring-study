package StackAndQueue.solution;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CurriculumDesign {
    public String solution(String need, String plan) {
        String answer = "YES";
        Queue<Character> q = new LinkedList<>();
        for (char c : need.toCharArray()) {
            q.offer(c);
        }
        for (char c : plan.toCharArray()) {
            if (q.contains(c) && c != q.poll()) {
                return "NO";
            }
        }
        if (!q.isEmpty()) return "NO";
        return answer;
    }
    public static void main(String[] args) {
        CurriculumDesign t = new CurriculumDesign();
        Scanner kb = new Scanner(System.in);
        String need = kb.next();
        String plan = kb.next();
        System.out.println(t.solution(need,plan));
    }
}
