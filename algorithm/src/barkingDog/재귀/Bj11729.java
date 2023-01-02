package barkingDog.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 하노이 탑 이동 순서 */
public class Bj11729 {

    static int cnt = 0;
    static StringBuilder sb = new StringBuilder();

    private static void recursion(int a, int b, int n) {
        cnt++;
        if (n == 1) {
            sb.append(a + " " + b).append("\n");
        } else {
            recursion(a, 6 - a - b, n - 1);
            sb.append(a + " " + b).append("\n");
            recursion(6 - a - b, b, n - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        recursion(1, 3, n);
        System.out.println(cnt);
        System.out.println(sb);
    }
}
