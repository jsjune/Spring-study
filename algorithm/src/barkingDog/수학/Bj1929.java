package barkingDog.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 소수 구하기 */
public class Bj1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[] arr = new int[end + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (prime(i)) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean prime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
