package barkingDog.우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/* 카드 정렬하기 */
public class Bj1715 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Long> q = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            q.add(Long.parseLong(br.readLine()));
        }

        long sum = 0;
        while (q.size() > 1) {
            Long a = q.poll();
            Long b = q.poll();
            sum += a + b;
            q.add(a+b);
        }
        System.out.println(sum);

    }
}
