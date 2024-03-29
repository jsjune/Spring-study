package sortingAndSearching;

import java.util.Scanner;

public class InsertSort {
    public int[] solution(int n, int[] arr) {
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 1; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j - 1] = tmp;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        InsertSort t = new InsertSort();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        for (int num : t.solution(n, arr)) {
            System.out.print(num + " ");
        }
    }
}
