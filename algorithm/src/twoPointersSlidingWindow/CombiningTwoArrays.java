package twoPointersSlidingWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CombiningTwoArrays {
    public ArrayList<Integer> solution(int a, int[] arrayA, int b, int[] arrayB) {
        ArrayList<Integer> answer = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < a + b; i++) {
            if (i < a) {
                answer.add(arrayA[i]);
            } else {
                answer.add(arrayB[cnt]);
                cnt++;
            }
        }
        Collections.sort(answer);
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
