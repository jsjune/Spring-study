package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 행렬 */
public class Bj1080 {
    static int row, col;
    static int[][] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        A = new int[row][col];
        B = new int[row][col];

        for (int i = 0; i < row; i++) {
            String[] num = br.readLine().split("");
            for (int j = 0; j < col; j++) {
                A[i][j] = Integer.parseInt(num[j]);
            }
        }

        for (int i = 0; i < row; i++) {
            String[] num = br.readLine().split("");
            for (int j = 0; j < col; j++) {
                B[i][j] = Integer.parseInt(num[j]);
            }
        }

        int cnt = 0;
        for (int i = 0; i < row - 2; i++) {
            for (int j = 0; j < col - 2; j++) {
                if (A[i][j] != B[i][j]) {
                    change(i, j);
                    cnt++;
                }
            }
        }

        boolean check = true;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (A[i][j] != B[i][j]) {
                    check = false;
                    break;
                }
            }
        }

        System.out.println(check ? cnt : -1);
    }

    private static void change(int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                A[i][j] = A[i][j]^1;
            }
        }
    }
}
