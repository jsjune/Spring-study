package baekjoon.재귀.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1992 {
    static StringBuilder sb = new StringBuilder();
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }
        solution(0, 0, n);
        System.out.println(sb);
    }

    private static void solution(int x, int y, int size) {
        if (numberCheck(x, y, size)) {
            if (arr[x][y] == 0) {
                sb.append("0");
            } else if (arr[x][y] == 1) {
                sb.append("1");
            }
            return;
        }

        int newSize = size / 2;
        sb.append("(");
        solution(x, y, newSize); // 1사분면
        solution(x, y + newSize, newSize); // 2사분면
        solution(x + newSize, y, newSize); // 3사분면
        solution(x + newSize, y + newSize, newSize); // 4사분면
        sb.append(")");
    }

    private static boolean numberCheck(int x, int y, int size) {
        int num = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != num) {
                    return false;
                }
            }
        }
        return true;
    }
}
