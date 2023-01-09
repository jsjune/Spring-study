package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* RGB거리 */
public class Bj1149 {

    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;
    static int[][] dp;

    static int solution(int n, int[][] arr) {
        for (int i = 1; i < n; i++) {
            arr[i][RED] += Math.min(arr[i - 1][GREEN], arr[i - 1][BLUE]);
            arr[i][GREEN] += Math.min(arr[i - 1][RED], arr[i - 1][BLUE]);
            arr[i][BLUE] += Math.min(arr[i - 1][RED], arr[i - 1][GREEN]);
        }
        return Math.min(Math.min(arr[n - 1][0], arr[n - 1][1]), arr[n - 1][2]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][RED] = Integer.parseInt(st.nextToken());
            arr[i][GREEN] = Integer.parseInt(st.nextToken());
            arr[i][BLUE] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(n,arr));
    }
}
