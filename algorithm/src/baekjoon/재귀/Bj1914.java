package baekjoon.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Bj1914 {
    static int cnt = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger res = new BigInteger("2");
        int n = Integer.parseInt(br.readLine());
        if (n <= 20) {
            solution(n, 1, 3, 2);
            sb.insert(0, (int) Math.pow(2, n) - 1 + "\n");
            System.out.println(sb);
        } else {
            solution(n, 1, 3, 2);
            res = res.pow(n).subtract(new BigInteger("1"));
            System.out.println(res);
        }
    }

    // start에서 to로 via를 거쳐 총 n개의 원반을 운반
    private static void solution(int n, int start, int to, int via) {
        if (n == 1) {
            sb.append(start + " " + to).append("\n");
        } else {
            solution(n - 1, start, via, to);
            sb.append(start + " " + to).append("\n");
            solution(n - 1, via, to, start);
        }
    }
}
