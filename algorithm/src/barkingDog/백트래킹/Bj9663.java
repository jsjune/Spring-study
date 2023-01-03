package barkingDog.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* N-Queen */
public class Bj9663 {

    static int n, cnt = 0;
    static int[] check = new int[40];
    static int[] check1 = new int[40];
    static int[] check2 = new int[40];

    private static void backtracking(int k) {
        if (k == n) {
            cnt++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (check[i] != 1 && check1[i + k] != 1 && check2[k - i + n - 1] != 1) {
                check[i] = 1;
                check1[i + k] = 1;
                check2[k - i + n - 1] = 1;
                backtracking(k + 1);
                check[i] = 0;
                check1[i + k] = 0;
                check2[k - i + n - 1] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        backtracking(0);
        System.out.println(cnt);
    }
}
