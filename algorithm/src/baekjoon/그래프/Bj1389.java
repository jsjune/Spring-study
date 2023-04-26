package baekjoon.그래프;
/*
 * 케빈 베이컨의 6단계 볍칙
 * https://www.acmicpc.net/problem/1389
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1389 {
    static ArrayList<Integer>[] graph;
    static int[] dist;
    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        solution();
    }

    private static void solution() {
        int minCnt = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = BFS(i);
            if (minCnt > cnt) {
                minCnt = cnt;
                minIdx = i;
            }
        }
        System.out.println(minIdx);
    }

    private static int BFS(int start) {
        Arrays.fill(dist, -1);
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            Integer x = q.poll();
            for (int num : graph[x]) {
                if (dist[num] != -1) {
                    continue;
                }
                dist[num] = dist[x] + 1;
                cnt += dist[num];
                q.add(num);
            }
        }
        return cnt;
    }

}
