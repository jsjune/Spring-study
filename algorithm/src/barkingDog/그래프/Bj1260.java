package barkingDog.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* DFS와 BFS */
public class Bj1260 {

    static int n, m, v;
    static int[][] arr;
    static int[] check;
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];
        check = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = arr[b][a] = 1;
        }

        dfs(v);
        check = new int[n + 1];

        sb.append("\n");
        bfs(v);
        System.out.println(sb);

    }

    private static void dfs(int v) {
        sb.append(v + " ");
        check[v] = 1;
        for (int i = 1; i <= n; i++) {
            if (arr[v][i] == 1 && check[i] != 1) {
                check[i] = 1;
                dfs(i);
            }
        }
    }

    private static void bfs(int v) {
        q.add(v);
        check[v] = 1;
        while (!q.isEmpty()) {
            Integer num = q.poll();
            sb.append(num + " ");
            for (int i = 1; i <= n; i++) {
                if (arr[num][i] == 1 && check[i] != 1) {
                    check[i] = 1;
                    q.add(i);
                }
            }
        }
    }
}
