package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj7569 {

    static int[][][] board,dis;
    static int n, m, z;
    static int[] dx = {-1, 0, 1, 0, 0, 0}, dy = {0, 1, 0, -1, 0, 0}, dz = {0, 0, 0, 0, 1, -1};
    static Queue<Point> q = new LinkedList<>();

    public static int bfs() {
        while (!q.isEmpty()) {
            Point tmp = q.poll();
            for (int i = 0; i < 6; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                int nz = tmp.z + dz[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && nz >= 0 && nz < z) {
                    if (board[nz][nx][ny] == 0) {
                        board[nz][nx][ny] = 1;
                        q.offer(new Point(nx, ny, nz));
                        dis[nz][nx][ny] = dis[tmp.z][tmp.x][tmp.y]+1;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (board[i][j][k] == 0) {
                        return -1;
                    }
                    max = Math.max(dis[i][j][k], max);
                }
            }
        }
        if (max == 1) {
            return 0;
        } else {
            return max-1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());
        board = new int[z][n][m];
        dis = new int[z][n][m];
        for (int i = 0; i < z; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                    if (board[i][j][k] == 1) {
                        dis[i][j][k] = 1;
                        q.offer(new Point(j, k, i));
                    }
                }
            }
        }
        System.out.println(bfs());

    }

    private static class Point {

        int x;
        int y;
        int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}
