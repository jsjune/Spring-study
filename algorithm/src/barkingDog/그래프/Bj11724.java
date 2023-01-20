package barkingDog.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj11724 {
    static int N,M;
    static int[][] arr;
    static int[] check;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        check = new int[N + 1];
        arr = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = arr[b][a] = 1;
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (check[i] == 0) {
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int n) {
        check[n]=1;
        for (int i = 1; i <= N; i++) {
            if (arr[n][i] == 1 && check[i] != 1) {
                check[i]=1;
                dfs(i);
            }
        }
    }
}
