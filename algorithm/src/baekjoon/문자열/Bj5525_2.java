package baekjoon.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj5525_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        char[] chars = br.readLine().toCharArray();
        int cnt = 0;
        int result = 0;
        for (int i = 1; i < m - 1; i++) {
            if (chars[i - 1] == 'I' && chars[i] == 'O' && chars[i + 1] == 'I') {
                cnt++;
                if (cnt == n) {
                    cnt--;
                    result++;
                }
                i++;
            } else {
                cnt = 0;
            }
        }

        System.out.println(result);
    }
}
