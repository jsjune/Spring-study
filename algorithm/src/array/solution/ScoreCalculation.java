package array.solution;

import java.util.Scanner;

public class ScoreCalculation {
    public int solution(int n, int[] arr) {
        int answer = 0, cnt = 0;
        for (int num : arr) {
            if (num == 1) {
                cnt++;
                answer+=cnt;
            } else {
                cnt=0;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        ScoreCalculation t = new ScoreCalculation();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(n,arr));
    }
}
