package baekjoon.배낭문제_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj1535 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] hp = new int[n + 1];
        int[] happy = new int[n + 1];
        int[][] dp = new int[n + 1][100];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            hp[i] = Integer.parseInt(st1.nextToken());
            happy[i] = Integer.parseInt(st2.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 99; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - hp[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - hp[i]] + happy[i]);
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 99; j++) {
                System.out.print(dp[i][j]+", ");
            }
            System.out.println();
        }
        System.out.println(dp[n][99]);

    }


}
