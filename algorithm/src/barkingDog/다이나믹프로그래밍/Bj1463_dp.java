package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 1로 만들기 */
public class Bj1463_dp {
    static int[] dy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dy = new int[n + 1];
        dy[1]=0;
        for (int i = 2; i <= n; i++) {
            dy[i] = dy[i - 1] + 1;
            if (i % 2 == 0) {
                dy[i] = Math.min(dy[i], dy[i / 2] + 1);
            }
            if (i % 3 == 0) {
                dy[i] = Math.min(dy[i], dy[i / 3] + 1);
            }
        }
        System.out.println(dy[n]);
    }
}
