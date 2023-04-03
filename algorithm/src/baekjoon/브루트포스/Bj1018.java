package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if (split[j].equals("B")) {
                    board[i][j] = 1;
                }
                if (split[j].equals("W")) {
                    board[i][j] = 0;
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < n - 7; i++) {
            for (int j = 0; j < m - 7; j++) {
                int count = 0;
                int first = board[i][j];
                for (int k = i; k < i + 8; k++) {
                    for (int l = j; l < j + 8; l++) {
                        if (board[k][l] != first) {
                            count++;
                        }
                        first = first == 1 ? 0 : 1;
                    }
                    first = first == 1 ? 0 : 1;
                }
                count = Math.min(count, 64 - count);
                answer = Math.min(answer, count);
            }
        }
        System.out.println(answer);
    }
}
