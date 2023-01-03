package barkingDog.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 부분수열의 합 */
public class Bj1182 {

    static int n, s, cnt = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        backtracking(0, 0);
        if (s == 0) {
            cnt--;
        }
        System.out.println(cnt);
    }

    private static void backtracking(int k, int sum) {
        if (k == n) {
            if (sum == s) {
                cnt++;
            }
            return;
        }
        backtracking(k + 1, sum);
        backtracking(k + 1, sum + arr[k]);
    }
}
