package array;

import java.util.Scanner;

public class FibonacciSequence {
    public int[] solution(int n) {
        int[] answer = new int[n];
        answer[0]=1;
        answer[1]=1;
        for (int i = 2; i < n; i++) {
            answer[i] = answer[i - 1] + answer[i - 2];
        }
        return answer;
    }

    public static void main(String[] args) {
        FibonacciSequence t = new FibonacciSequence();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        for (int x : t.solution(n)) {
            System.out.print(x + " ");
        }
    }
}