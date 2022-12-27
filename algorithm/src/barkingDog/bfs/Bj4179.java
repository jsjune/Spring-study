package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 불! */
public class Bj4179 {

    static char[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, m, cnt;
    static Queue<Point> jh, fire;

    public static boolean bfs() {
        while (!jh.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                Point tmp = fire.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue;
                    }
                    if (board[nx][ny] == '#' || board[nx][ny] == 'F') {
                        continue;
                    }
                    board[nx][ny] = 'F';
                    fire.add(new Point(nx, ny,tmp.c+1));
                }
            }
            size = jh.size();
            for (int i = 0; i < size; i++) {
                Point tmp = jh.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        cnt=tmp.c+1;
                        return true;
                    }
                    if (board[nx][ny] == '#' || board[nx][ny] == 'F' || board[nx][ny] == 'J') {
                        continue;
                    }
                    board[nx][ny] = 'J';
                    jh.add(new Point(nx, ny,tmp.c+1));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        jh = new LinkedList<>();
        fire = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                board[i][j] = c[j];
                if (board[i][j] == 'J') {
                    jh.offer(new Point(i, j, 0));
                } else if (board[i][j] == 'F') {
                    fire.offer(new Point(i, j, 0));
                }
            }
        }
        if (bfs()) {
            System.out.println(cnt);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }
    private static class Point{
        int x;
        int y;
        int c;

        public Point(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

}
