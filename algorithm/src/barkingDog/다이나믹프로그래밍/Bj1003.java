package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 피보나치 함수 */
public class Bj1003 {
    static int cnt0 = 0;
    static int cnt1 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                int cnt0 = get0Count(num);
                int cnt1 = get1Count(num);
                sb.append(cnt0 + " " + cnt1).append("\n");
            } else {
                int[] dp0 = new int[2];
                dp0[0] = 1;
                dp0[1] = 0;
                int[] dp1 = new int[2];
                dp1[0] = 0;
                dp1[1] = 1;
                sb.append(dp0[num] + " " + dp1[num]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int get0Count(int num) {
        int[] dp = new int[num +1];
        dp[0] = 1;
        dp[1] = 0;
        for (int j = 2; j <= num; j++) {
            dp[j] = dp[j - 1] + dp[j - 2];
        }
        return dp[num];
    }

    private static int get1Count(int num) {
        int[] dp = new int[num +1];
        dp[0] = 0;
        dp[1] = 1;
        for (int j = 2; j <= num; j++) {
            dp[j] = dp[j - 1] + dp[j - 2];
        }
        return dp[num];
    }


}
