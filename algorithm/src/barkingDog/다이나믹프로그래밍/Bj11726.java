package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 2Xn 타일링 */
public class Bj11726 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        arr[1] = 1;
        if (n >= 2) {
            arr[2] = 2;
            for (int i = 3; i <= n; i++) {
                arr[i] = (arr[i - 1] + arr[i - 2]) % 10_007;
            }
        }
        System.out.println(arr[n]);
    }
}
