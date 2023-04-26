package baekjoon.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    로또
    https://www.acmicpc.net/problem/6603
*/
public class Bj6603 {
    static int[] arr;
    static boolean[] visited;
    static int k;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }
            arr = new int[k];
            visited = new boolean[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            comb(0, 6);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void comb(int start, int r) {
        if (r == 0) {
            for (int i = 0; i < k; i++) {
                if (visited[i] == true) {
                    sb.append(arr[i] + " ");
                }
            }
            sb.append("\n");
        } else {
            for (int i = start; i < k; i++) {
                visited[i] = true;
                comb(i + 1, r - 1);
                visited[i] = false;
            }
        }
    }
}
