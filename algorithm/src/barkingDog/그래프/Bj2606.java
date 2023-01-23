package barkingDog.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 바이러스 */
public class Bj2606 {

    static int n, m, cnt = 0;
    static int[][] arr;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        check = new int[n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = arr[b][a] = 1;
        }

        dfs(1);
        System.out.println(cnt);
    }

    static void dfs(int a) {
        check[a] = 1;
        for (int i = 1; i <= n; i++) {
            if (arr[a][i] == 1 & check[i] != 1) {
                check[i] = 1;
                cnt++;
                dfs(i);
            }
        }
    }
}
