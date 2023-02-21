package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 수들의 합 */
public class Bj1789 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long ans = Long.parseLong(br.readLine());
        long sum = 0;
        int cnt = 0;
        for (int i = 1; i < Integer.MIN_VALUE - 1; i++) {
            sum+=i;
            cnt++;
            if (sum > ans) {
                break;
            }
        }
        System.out.println(cnt-1);
    }

}
