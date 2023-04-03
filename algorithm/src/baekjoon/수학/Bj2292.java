package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj2292 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int num = 1;
        int cnt = 1;
        while (true) {
            if (num >= n) {
                break;
            }
            num += cnt * 6;
            cnt++;
        }
        System.out.println(cnt);
    }
}
