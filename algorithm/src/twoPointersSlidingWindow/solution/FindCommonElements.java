package twoPointersSlidingWindow.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FindCommonElements {
    public ArrayList<Integer> solution(int a, int[] arr1, int b, int[] arr2) {
        ArrayList<Integer> answer = new ArrayList<>();
        return answer;
    }
    public static void main(String[] args) {
        FindCommonElements t = new FindCommonElements();
        Scanner kb = new Scanner(System.in);
        int a = kb.nextInt();
        int[] arr1 = new int[a];
        for (int i = 0; i < a; i++) {
            arr1[i] = kb.nextInt();
        }
        int b = kb.nextInt();
        int[] arr2 = new int[b];
        for (int i = 0; i < b; i++) {
            arr2[i] = kb.nextInt();
        }
        for (int num : t.solution(a, arr1, b, arr2)) {
            System.out.print(num + " ");
        }
    }
}
