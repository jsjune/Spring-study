package baekjoon.bfsAndDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 안전 영역 */
public class Bj2468 {

    static int n;
    static int arr[][], check[][];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static void bfs(int x, int y, int height) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + tmp[0];
                int ny = dy[i] + tmp[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && check[nx][ny] != 1
                    && arr[nx][ny] > height) {
                    check[nx][ny] = 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] > maxHeight) {
                    maxHeight = arr[i][j];
                }
            }
        }

        int max = 0;
        for (int height = 1; height <= maxHeight; height++) {
            check = new int[n][n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (check[i][j] != 1 && arr[i][j] > height) {
                        check[i][j] = 1;
                        cnt++;
                        bfs(i, j, height);
                    }
                }
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }
}
