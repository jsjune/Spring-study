package barkingDog.우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/* 절대값 힙 */
public class Bj11286 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Number> q = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (q.isEmpty()) {
                    sb.append(0).append("\n");

                } else {
                    Number poll = q.poll();
                    sb.append(poll.org).append("\n");
                }
            } else {
                q.add(new Number(x, Math.abs(x)));
            }
        }
        System.out.println(sb);
    }

    private static class Number implements Comparable<Number> {

        int org;
        int abs;

        public Number(int org, int abs) {
            this.org = org;
            this.abs = abs;
        }

        @Override
        public int compareTo(Number o) {
            if (this.abs == o.abs) {
                return this.org - o.org;
            }
            return this.abs - o.abs;
        }
    }

}
