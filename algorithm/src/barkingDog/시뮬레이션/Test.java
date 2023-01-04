package barkingDog.시뮬레이션;

import java.util.Arrays;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(Arrays.deepToString(arr));

        int[][] tmp = new int[m][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp[i][j] = arr[j][i];
            }
        }
        System.out.println(Arrays.deepToString(tmp));
    }
}
