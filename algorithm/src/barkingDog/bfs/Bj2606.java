package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/* 바이러스 */
public class Bj2606 {
    static int node[][];
    static int check[];

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        check[start]=1;
        queue.offer(start);
        int cnt=0;
        while (!queue.isEmpty()) {
            Integer x = queue.poll();
            for (int i = 1; i < node.length; i++) {
                if (node[x][i] == 1 && check[i] != 1) {
                    queue.offer(i);
                    check[i]=1;
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int m = Integer.parseInt(br.readLine()); // 간선의 수
        node = new int[n + 1][n + 1];
        check = new int[n + 1];
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            node[a][b] = 1;
            node[b][a] = 1;
        }
        bfs(1);
    }

}
