package baekjoon.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj5525 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String p = "I";
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            p += "OI";
        }
        int m = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int cnt = 0;
        if (m == 2 * n + 1) {
            if (p.equals(str)) {
                cnt++;
            }
        } else {
            for (int i = 0; i < m - (2 * n + 1); i++) {
                String tmp = str.substring(i, i + 2 * n + 1);
                if (tmp.equals(p)) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
