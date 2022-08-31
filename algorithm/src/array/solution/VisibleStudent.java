package array.solution;

import java.util.Scanner;

public class VisibleStudent {
    public int solution(int n, int[] nums) {
        int answer = 1;
        int high = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > high) {
                answer++;
                high = nums[i];
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        VisibleStudent t = new VisibleStudent();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = kb.nextInt();
        }
        System.out.println(t.solution(n,nums));
    }
}
