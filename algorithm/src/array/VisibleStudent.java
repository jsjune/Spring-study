package array;

import java.util.ArrayList;
import java.util.Scanner;

public class VisibleStudent {
    public int solution(int n, int[] nums) {
        int answer = 0;
        ArrayList<Integer> array = new ArrayList<>();
        int high = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > high) {
                array.add(nums[i]);
                high = nums[i];
            }
        }
        answer = array.size();
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
