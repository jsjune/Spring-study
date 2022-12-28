package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/* 적록색약 */
public class Bj10026 {

    static int n, cnt = 0;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static char[][] board;
    static int[][] check;
    static Queue<Point> q = new LinkedList<>();

    public static void bfs(int a, int b) {
        while (!q.isEmpty()) {
            Point tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (board[a][b] == 'R') {
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 'R'
                        && check[nx][ny] == 0) {
                        check[nx][ny] = 1;
                        q.offer(new Point(nx, ny));
                    }
                } else if (board[a][b] == 'G') {
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 'G'
                        && check[nx][ny] == 0) {
                        check[nx][ny] = 1;
                        q.offer(new Point(nx, ny));
                    }
                } else if (board[a][b] == 'B') {
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 'B'
                        && check[nx][ny] == 0) {
                        check[nx][ny] = 1;
                        q.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }

    public static void bfs2(int a, int b) {
        while (!q.isEmpty()) {
            Point tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (board[a][b] == 'R'||board[a][b] == 'G') {
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && (board[nx][ny] == 'R' || board[nx][ny] == 'G')
                        && check[nx][ny] == 0) {
                        check[nx][ny] = 1;
                        q.offer(new Point(nx, ny));
                    }
                }  else if (board[a][b] == 'B') {
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 'B'
                        && check[nx][ny] == 0) {
                        check[nx][ny] = 1;
                        q.offer(new Point(nx, ny));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                board[i][j] = c[j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 2; k++) {
            check = new int[n][n];
            cnt = 0;
            if (k == 0) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (board[i][j] == 'R' && check[i][j] == 0) {
                            cnt++;
                            q.offer(new Point(i, j));
                            check[i][j] = 1;
                            bfs(i, j);
                        } else if (board[i][j] == 'G' && check[i][j] == 0) {
                            cnt++;
                            q.offer(new Point(i, j));
                            check[i][j] = 1;
                            bfs(i, j);
                        } else if (board[i][j] == 'B' && check[i][j] == 0) {
                            cnt++;
                            q.offer(new Point(i, j));
                            check[i][j] = 1;
                            bfs(i, j);
                        }
                    }
                }
                sb.append(cnt).append(" ");
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if ((board[i][j] == 'R'|| board[i][j] == 'G') && check[i][j] == 0) {
                            cnt++;
                            q.offer(new Point(i, j));
                            check[i][j] = 1;
                            bfs2(i, j);
                        } else if (board[i][j] == 'B' && check[i][j] == 0) {
                            cnt++;
                            q.offer(new Point(i, j));
                            check[i][j] = 1;
                            bfs2(i, j);
                        }
                    }
                }
                sb.append(cnt);
            }
        }
        System.out.println(sb);
    }

    private static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
