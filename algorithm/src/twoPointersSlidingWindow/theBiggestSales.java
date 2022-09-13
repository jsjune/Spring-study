package twoPointersSlidingWindow;

import java.util.Scanner;

public class theBiggestSales {
    public int solution(int a, int b, int[] arr) {
        int answer = Integer.MIN_VALUE;
        int p = 0, max = 0;
        for (int i = 0; i < a; i++) {
            max += arr[i];
            if (p >= b) {
                max -= arr[i - b];
            }
            p++;
            answer = Math.max(answer, max);
        }
        return answer;
    }

    public static void main(String[] args) {
        theBiggestSales t = new theBiggestSales();
        Scanner kb = new Scanner(System.in);
        int a = kb.nextInt();
        int b = kb.nextInt();
        int[] arr = new int[a];
        for (int i = 0; i < a; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(a, b, arr));
    }
}
