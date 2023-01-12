package barkingDog.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 소수 찾기 */
public class Bj1978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[1001];
        arr[0] = arr[1] = 1;
        for (int i = 2; i * i <= 1000; i++) {
            if (arr[i] == 0) {
                for (int j = i * i; j <= 1000; j += i) {
                    arr[j] = 1;
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (arr[num] == 0) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

