package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MaxSumOfGridPlates {
    private int sum = 0;
    public int solution(int n, int[][] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        DiagonalSum1(n, arr, list);
        DiagonalSum2(n, arr, list);
        rowSum(n, arr, list);
        columnSum(n, arr, list);
        Collections.sort(list,Collections.reverseOrder());
        return list.get(0);
    }

    private void columnSum(int n, int[][] arr, ArrayList<Integer> list) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += arr[j][i];
            }
            list.add(sum);
            sum = 0;
        }
    }

    private void rowSum(int n, int[][] arr, ArrayList<Integer> list) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum+= arr[i][j];
            }
            list.add(sum);
            sum = 0;
        }
    }

    private void DiagonalSum2(int n, int[][] arr, ArrayList<Integer> list) {
        for (int i = 0; i < n; i++) {
            sum+= arr[i][n-i-1];
        }
        list.add(sum);
        sum = 0;
    }

    private void DiagonalSum1(int n, int[][] arr, ArrayList<Integer> list) {
        for (int i = 0; i < n; i++) {
            sum+= arr[i][i];
        }
        list.add(sum);
        sum = 0;
    }

    public static void main(String[] args) {
        MaxSumOfGridPlates t = new MaxSumOfGridPlates();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = kb.nextInt();
            }
        }
        System.out.println(t.solution(n, arr));
    }
}
