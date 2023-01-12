package barkingDog.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 이항 계수1 */
public class Bj11050 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int[][] arr = new int[n + 1][n + 1];
        arr[1][0]=1;
        arr[1][1]=1;
        for (int i = 2; i <= n; i++) {
            arr[i][0]=1;
            arr[i][i]=1;
            for (int j = 1; j <= n; j++) {
                arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
            }
        }
        System.out.println(arr[n][k]);
    }
}
