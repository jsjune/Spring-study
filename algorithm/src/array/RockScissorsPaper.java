package array;

import java.util.Scanner;

public class RockScissorsPaper {
    private final int scissors = 1;
    private final int rock = 2;
    private final int paper = 3;

    public String[] solution(int n, int[] personA, int[] personB) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            if (victoryA(personA, personB, i)) {
                answer[i] = "A";
            }
            if (victoryB(personA, personB, i)) {
                answer[i] = "B";
            }
            if (tie(personA, personB, i)) {
                answer[i] = "D";
            }
        }
        return answer;
    }

    private boolean victoryA(int[] nums1, int[] nums2, int i) {
        return (nums1[i] == scissors && nums2[i] == paper) || (nums1[i] == rock && nums2[i] == scissors) || (nums1[i] == paper && nums2[i] == rock);
    }

    private boolean victoryB(int[] nums1, int[] nums2, int i) {
        return (nums1[i] == paper && nums2[i] == scissors) || (nums1[i] == scissors && nums2[i] == rock) || (nums1[i] == rock && nums2[i] == paper);
    }

    private boolean tie(int[] nums1, int[] nums2, int i) {
        return (nums1[i] == scissors && nums2[i] == scissors) || (nums1[i] == rock && nums2[i] == rock) || (nums1[i] == paper && nums2[i] == paper);
    }

    public static void main(String[] args) {
        RockScissorsPaper t = new RockScissorsPaper();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] nums1 = new int[n];
        for (int i = 0; i < n; i++) {
            nums1[i] = kb.nextInt();
        }
        int[] nums2 = new int[n];
        for (int i = 0; i < n; i++) {
            nums2[i] = kb.nextInt();
        }
        for (String x : t.solution(n, nums1, nums2)) {
            System.out.println(x);
        }
    }
}
