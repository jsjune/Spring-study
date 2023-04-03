package baekjoon.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj2775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[k + 1][n];
            for (int j = 0; j < n; j++) {
                arr[0][j] = j + 1;
            }
            for (int j = 1; j < k + 1; j++) {
                arr[j][0]=1;
                for (int l = 1; l < n; l++) {
                    arr[j][l] = arr[j - 1][l] + arr[j][l - 1];
                }
            }
            sb.append(arr[k][n-1]).append("\n");
        }
        System.out.println(sb);
    }
}
