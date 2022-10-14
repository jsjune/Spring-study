package sortingAndSearching;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public int solution(int n, int target, int[] arr) {
        Arrays.sort(arr);
        int start = 0;
        int end = n - 1;
        int mid = (start + end) / 2;
        while (start <= end) {
            if (arr[mid] == target) {
                return mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch t = new BinarySearch();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int target = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(n, target, arr));
    }
}
