package sortingAndSearching;

import java.util.Scanner;

public class SelectionSort {
    public int[] solution(int n, int[] arr) {
        int tmp = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        SelectionSort t = new SelectionSort();
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
