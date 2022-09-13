package twoPointersSlidingWindow.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FindCommonElements {
    public ArrayList<Integer> solution(int a, int[] arr1, int b, int[] arr2) {
        ArrayList<Integer> answer = new ArrayList<>();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int p1=0,p2=0;
        while (p1 < a && p2 < b) {
            if (arr1[p1] == arr2[p2]) {
                answer.add(arr1[p1++]);
                p2++;
            } else if (arr1[p1] < arr2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
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
