package sortingAndSearching;

import java.util.Scanner;

public class DuplicatesCheck {
    public char solution(int n, int[] arr) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    return 'D';
                }
            }
        }
        return 'U';
    }
    public static void main(String[] args) {
        DuplicatesCheck t = new DuplicatesCheck();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(n,arr));
    }
}
