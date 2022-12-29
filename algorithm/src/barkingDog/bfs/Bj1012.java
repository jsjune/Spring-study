package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 유기농 배추 */
public class Bj1012 {

    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] board;
    static int n, m, cnt;

    public static void bfs(int a, int b) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(a, b));
        while (!q.isEmpty()) {
            Point tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 1) {
                    board[nx][ny] = 0;
                    q.offer(new Point(nx, ny));
                }
            }
        }
    }

    private static void solution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    cnt++;
                    board[i][j] = 0;
                    bfs(i, j);
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            board = new int[n][m];
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                board[b][a] = 1;
            }
            solution();
            sb.append(cnt).append("\n");
            cnt=0;
        }
        System.out.println(sb);
    }

}
