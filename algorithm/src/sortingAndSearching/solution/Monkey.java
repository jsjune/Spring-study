package sortingAndSearching.solution;

import java.util.Arrays;
import java.util.Scanner;

public class Monkey {
    public void solution(int n, int[] arr) {
        int[] tmp = arr.clone();
        Arrays.sort(tmp);
        for (int i = 0; i < n; i++) {
            if (arr[i] != tmp[i]) {
                System.out.print(i+1 + " ");
            }
        }
    }
    public static void main(String[] args) {
        Monkey t = new Monkey();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        t.solution(n, arr);
    }
}
