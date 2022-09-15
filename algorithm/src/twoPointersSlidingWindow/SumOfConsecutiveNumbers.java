package twoPointersSlidingWindow;

import java.util.Scanner;

public class SumOfConsecutiveNumbers {
    public int solution(int n) {
        int answer = 0, sum = 0, cnt = 0;
        for (int i = 1; i <= n/2+1; i++) {
            sum += i;
            while (sum >= n) {
                sum -= cnt;
                cnt++;
                if (sum == n) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        SumOfConsecutiveNumbers t = new SumOfConsecutiveNumbers();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        System.out.println(t.solution(n));
    }
}
