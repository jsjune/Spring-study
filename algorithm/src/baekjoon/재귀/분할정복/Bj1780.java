package baekjoon.재귀.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1780 {
    static int[][] arr;
    static int minus1 = 0;
    static int zero = 0;
    static int plus1 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0, 0, n);
        System.out.println(minus1);
        System.out.println(zero);
        System.out.println(plus1);
    }

    private static void solution(int x, int y, int size) {
        if (numberCheck(x, y, size)) {
            if (arr[x][y] == -1) {
                minus1++;
            } else if (arr[x][y] == 0) {
                zero++;
            } else if (arr[x][y] == 1) {
                plus1++;
            }
            return;
        }

        int newSize = size / 3;
        solution(x, y, newSize);
        solution(x + newSize, y, newSize);
        solution(x + 2 * newSize, y, newSize);
        solution(x, y + newSize, newSize);
        solution(x + newSize, y + newSize, newSize);
        solution(x + 2 * newSize, y + newSize, newSize);
        solution(x, y + 2 * newSize, newSize);
        solution(x + newSize, y + 2 * newSize, newSize);
        solution(x + 2 * newSize, y + 2 * newSize, newSize);
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
