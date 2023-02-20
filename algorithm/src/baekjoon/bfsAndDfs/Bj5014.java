package baekjoon.bfsAndDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 스타트링크 */
public class Bj5014 {

    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        visited = new int[F + 1];

        bfs(F, S, G, U, D);
    }

    private static void bfs(int F, int S, int G, int U, int D) {
        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        visited[S] = 1;
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            if (cur == G) {
                System.out.println(visited[cur] - 1);
            }
            if (cur + U <= F && visited[cur + U] == 0) {
                visited[cur + U] = visited[cur] + 1;
                q.add(cur + U);
            }
            if (cur - D >= 0 && visited[cur - D] == 0) {
                visited[cur - D] = visited[cur] + 1;
                q.add(cur - D);
            }
        }
        if (visited[G] == 0) {
            System.out.println("use the stairs");
        }
    }
}
