package baekjoon.bfsAndDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
/* 단지번호붙이기 */
public class Bj2667 {
    static int n,answer;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Integer> list = new ArrayList<>();
    static Queue<Point> q = new LinkedList<>();

    static void bfs(int a, int b) {
        q.offer(new Point(a, b));
        int cnt = 1;
        while (!q.isEmpty()) {
            Point tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && arr[nx][ny]==1) {
                    arr[nx][ny] = 0;
                    q.offer(new Point(nx, ny));
                    cnt++;
                }
            }
        }
        list.add(cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] number = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(number[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    answer++;
                    arr[i][j] = 0;
                    bfs(i, j);
                }
            }
        }
        Collections.sort(list);
        System.out.println(answer);
        list.forEach(System.out::println);
    }

    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
