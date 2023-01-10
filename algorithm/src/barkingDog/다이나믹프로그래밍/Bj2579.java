package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 계단 오르기 */
public class Bj2579 {
    static int[] dy;

    public static int solution(int n, int[] arr) {
        dy[1] = arr[1];
        if (n >= 2) {
            dy[2] = arr[1] + arr[2];
            for (int i = 3; i <= n; i++) {
                dy[i] = Math.max(dy[i - 2], dy[i - 3] + arr[i - 1]) + arr[i];
            }
        }
        return dy[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        dy = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(n, arr));
    }
}
