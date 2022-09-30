package StackAndQueue.solution;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SavingPrincess {
    public int solution(int n, int k) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        while (!queue.isEmpty()) {
            for (int i = 0; i < k - 1; i++) queue.add(queue.poll());
            queue.poll();
            if(queue.size()==1) answer = queue.poll();
        }
        return answer;
    }

    public static void main(String[] args) {
        SavingPrincess t = new SavingPrincess();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        System.out.println(t.solution(n, k));
    }
}
