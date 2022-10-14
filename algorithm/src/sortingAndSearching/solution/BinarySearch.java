package sortingAndSearching.solution;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public int solution(int n, int target, int[] arr) {
        int answer = 0;
        Arrays.sort(arr);
        int lt = 0, rt = n - 1;
        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (arr[mid] == target) {
                answer =  mid +1;
                break;
            }
            if (arr[mid] > target) {
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }
        return answer;
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
