package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj16953_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        int cnt = 1;
        while (a!=b) {
            if (a > b) {
                cnt = -1;
                break;
            }
            if (b % 10 != 1 && b % 2 != 0) {
                cnt = -1;
                break;
            }
            cnt++;
            if (b % 10 == 1) {
                b /= 10;
            } else {
                b /= 2;
            }
        }
        System.out.println(cnt);
    }
}
