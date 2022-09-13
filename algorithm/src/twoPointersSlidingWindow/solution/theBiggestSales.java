package twoPointersSlidingWindow.solution;

import java.util.Scanner;

public class theBiggestSales {
    public int solution(int a, int b, int[] arr) {
        int answer = 0, sum = 0;
        for (int i = 0; i < b; i++) {
            sum += arr[i];
        }
        answer = sum;
        for (int i = b; i < a; i++) {
            sum += arr[i] - arr[i - b];
            answer = Math.max(answer, sum);
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
