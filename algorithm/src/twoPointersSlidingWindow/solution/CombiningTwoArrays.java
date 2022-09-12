package twoPointersSlidingWindow.solution;

import java.util.ArrayList;
import java.util.Scanner;

public class CombiningTwoArrays {
    public ArrayList<Integer> solution(int a, int[] arrayA, int b, int[] arrayB) {
        ArrayList<Integer> answer = new ArrayList<>();
        int p1=0,p2=0;
        while (p1 < a && p2 < b) {
            if (arrayA[p1] < arrayB[p2]) {
                answer.add(arrayA[p1++]);
            } else {
                answer.add(arrayB[p2++]);
            }
        }
        while (p1<a) answer.add(arrayA[p1++]);
        while (p2<b) answer.add(arrayB[p2++]);
        return answer;
    }

    public static void main(String[] args) {
        CombiningTwoArrays t = new CombiningTwoArrays();
        Scanner kb = new Scanner(System.in);
        int a = kb.nextInt();
        int[] arrayA = new int[a];
        for (int i = 0; i < a; i++) {
            arrayA[i] = kb.nextInt();
        }
        int b = kb.nextInt();
        int[] arrayB = new int[b];
        for (int i = 0; i < b; i++) {
            arrayB[i] = kb.nextInt();
        }
        for (int n : t.solution(a, arrayA, b, arrayB)) {
            System.out.print(n + " ");
        }
    }
}
