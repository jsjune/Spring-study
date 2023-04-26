package baekjoon.재귀.분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj2630 {
    static int white = 0;
    static int blue = 0;
    static int[][] arr;
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
        System.out.println(white);
        System.out.println(blue);
    }

    private static void solution(int x, int y, int size) {
        if (colorCheck(x, y, size)) {
            if (arr[x][y] == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }
        int newSize = size / 2;
        solution(x, y, newSize);
        solution(x, y + newSize, newSize);
        solution(x + newSize, y, newSize);
        solution(x + newSize, y + newSize, newSize);
    }

    private static boolean colorCheck(int x, int y, int size) {
        int color = arr[x][y];
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (arr[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
