package barkingDog.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 스티커 붙이기 */
public class Bj18808 {
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[a][b];
            for (int j = 0; j < a; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < b; l++) {
                    sticker[j][l] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }


}
