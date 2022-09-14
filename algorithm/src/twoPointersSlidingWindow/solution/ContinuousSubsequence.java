package twoPointersSlidingWindow.solution;

import java.util.Scanner;

public class ContinuousSubsequence {
    public int solution(int a, int b, int[] arr) {
        int answer = 0, sum = 0, cnt=0;
        for (int i = 0; i < a; i++) {
            sum += arr[i];
            if (sum == b) {
                answer++;
            }
            while (sum >= b) {
                sum -= arr[cnt++];
                if (sum == b) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        ContinuousSubsequence t = new ContinuousSubsequence();
        Scanner kb = new Scanner(System.in);
        int a = kb.nextInt();
        int b = kb.nextInt();
        int[] arr = new int[a];
        for (int i = 0; i < a; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(a,b,arr));
    }
}
