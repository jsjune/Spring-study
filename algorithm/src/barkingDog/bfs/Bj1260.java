package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj1260 {
    static int[][] arr;
    static int[] check;
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void dfs(int v) {
        check[v] = 1;
        sb.append(v + " ");
        for (int i = 1; i < arr.length; i++) {
            if (arr[v][i] == 1 && check[i] != 1) {
                dfs(i);
            }
        }
    }

    public static void bfs(int v) {
        q.offer(v);
        check[v] = 1;
        while (!q.isEmpty()) {
            Integer num = q.poll();
            sb.append(num + " ");
            for (int i = 1; i < arr.length; i++) {
                if (arr[num][i] == 1 && check[i] != 1) {
                    q.add(i);
                    check[i] = 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];
        check = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b]=arr[b][a]=1;
        }
        dfs(v);
        sb.append("\n");
        check = new int[n + 1];
        bfs(v);
        System.out.println(sb);
    }

}
