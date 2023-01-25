package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 파도반 수열 */
public class Bj9461 {

    static long[] arr = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr[1]=1;
        arr[2]=1;
        arr[3]=1;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            for (int j = 4; j <= a; j++) {
                arr[j] = arr[j - 2] + arr[j - 3];
            }
            System.out.println(arr[a]);
        }
    }
}
