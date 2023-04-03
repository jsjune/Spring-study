package baekjoon.큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Queue<Point> q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                q.add(new Point(j, num));
            }

            int cnt = 0;
            while (true) {
                Point now = q.poll();
                boolean flag = true;
                for (Point point : q) {
                    if (point.important > now.important) {
                        flag = false;
                    }
                }
                if (flag) {
                    cnt++;
                    if (now.idx == m) {
                        break;
                    }
                } else {
                    q.add(now);
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static class Point {
        int idx;
        int important;

        public Point(int idx, int important) {
            this.idx = idx;
            this.important = important;
        }
    }
}
