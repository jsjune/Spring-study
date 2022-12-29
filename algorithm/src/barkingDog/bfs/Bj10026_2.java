package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 적록색약 */
/* DFS로 풀어볼것 */
public class Bj10026_2 {

    static int n, cnt = 0;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static char[][] board;
    static int[][] check;

    public static void dfs(int a, int b) {
        check[a][b]=1;
        char color = board[a][b];
        for (int i = 0; i < 4; i++) {
            int nx = a + dx[i];
            int ny = b + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == color
                && check[nx][ny] == 0) {
                dfs(nx, ny);
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
                        if (check[i][j] == 0) {
                            dfs(i, j);
                            cnt++;
                        }
                    }
                }
                sb.append(cnt).append(" ");
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (board[i][j] == 'G') {
                            board[i][j] = 'R';
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (check[i][j] == 0) {
                            dfs(i, j);
                            cnt++;
                        }
                    }
                }
                sb.append(cnt);
            }
        }
        System.out.println(sb);
    }

}
