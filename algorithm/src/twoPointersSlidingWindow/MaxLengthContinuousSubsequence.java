package twoPointersSlidingWindow;

import java.util.Scanner;

public class MaxLengthContinuousSubsequence {
    public int solution(int n, int k, int[] arr) {
        int answer = 0;

        return answer;
    }

    public static void main(String[] args) {
        MaxLengthContinuousSubsequence t = new MaxLengthContinuousSubsequence();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int k = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(n, k, arr));
    }
}
