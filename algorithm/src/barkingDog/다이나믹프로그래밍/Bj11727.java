package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 2Xn 타일링 2 */
public class Bj11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if (n > 2) {
            dp[2] = 3;
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10_007;
            }
        } else if (n == 2) {
            dp[2] = 3;
        }
        System.out.println(dp[n]);
    }
}
