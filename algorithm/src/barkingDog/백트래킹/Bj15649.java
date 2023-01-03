package barkingDog.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* N과 M (1) */
public class Bj15649 {
    static int n,m;
    static int[] arr = new int[10];
    static int[] check = new int[10];
    static StringBuilder sb = new StringBuilder();

    private static void backtracking(int k) {
        if (k == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (check[i]!=1) {
                arr[k] = i;
                check[i] = 1;
                backtracking(k+1);
                check[i] = 0;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        backtracking(0);
        System.out.println(sb);
    }

}
