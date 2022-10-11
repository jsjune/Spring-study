package sortingAndSearching.solution;

import java.util.Arrays;
import java.util.Scanner;

public class DuplicatesCheck {
    public char solution(int n, int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < n - 1; i++) {
            if(arr[i]==arr[i+1]) return 'D';
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
