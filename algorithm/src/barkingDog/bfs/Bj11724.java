package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj11724 {

    static int[][] arr;
    static int[] check;
    static int n, m, cnt = 0;

    public static void dfs(int a) {
        check[a] = 1;
        for (int i = 1; i <= n; i++) {
            if (arr[a][i] == 1 && check[i] != 1) {
                check[i] = 1;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];
        check = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = arr[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            if (check[i] == 0) {
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
