package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 불 */
public class Bj5427 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int n,m,count;
    static Queue<Point> sg, fire;

    public boolean bfs() {
        while (!sg.isEmpty()) {
            int size = fire.size();
            for (int i = 0; i < size; i++) {
                Point tmp = fire.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue;
                    }
                    if (map[nx][ny] == '#' || map[nx][ny] == '*') {
                        continue;
                    }
                    map[nx][ny] = '*';
                    fire.add(new Point(nx, ny, tmp.cnt + 1));
                }
            }
            size = sg.size();
            for (int i = 0; i < size; i++) {
                Point tmp = sg.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        count = tmp.cnt + 1;
                        return true;
                    }
                    if (map[nx][ny] == '#' || map[nx][ny] == '*' || map[nx][ny] == '@') {
                        continue;
                    }
                    map[nx][ny] = '@';
                    sg.add(new Point(nx, ny, tmp.cnt + 1));
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Bj5427 main = new Bj5427();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            sg = new LinkedList<>();
            fire = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][m];
            for (int j = 0; j < n; j++) {
                char[] c = br.readLine().toCharArray();
                for (int k = 0; k < m; k++) {
                    map[j][k] = c[k];
                    if (c[k] == '@') {
                        sg.offer(new Point(j, k, 0));
                    } else if (c[k] == '*') {
                        fire.offer(new Point(j, k, 0));
                    }
                }
            }
            if (main.bfs()) {
                sb.append(count).append("\n");
            } else {
                sb.append("IMPOSSIBLE").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static class Point{
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
