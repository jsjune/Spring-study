package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* A->B */
public class Bj16953 {
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        dfs(a, b, 0);
        System.out.println(answer);
    }

    private static void dfs(long a, long b, int cnt) {
        if (a == b) {
            answer = cnt + 1;
            return;
        }
        if (a > b) {
            return;
        }
        dfs(a * 2, b, cnt+1);
        dfs(a * 10 + 1, b, cnt+1);
    }
}
