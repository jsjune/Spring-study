package barkingDog.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* 회장뽑기 */
public class Bj2660 {

    static int n;
    static int[][] arr;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        check = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    arr[i][j] = 2_000;
                }
            }
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) {
                break;
            }
            arr[a][b] = arr[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (arr[j][k] > arr[j][i] + arr[i][k]) {
                        arr[j][k] = arr[j][i] + arr[i][k];
                    }
                }
            }
        }

        int[] scores = new int[n + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                scores[i] = Math.max(scores[i], arr[i][j]);
            }
            min = Math.min(min, scores[i]);
        }

        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (scores[i] == min) {
                list.add(i);
            }
        }
        sb.append(min + " " + list.size()).append("\n");

        for (Integer n : list) {
            sb.append(n + " ");
        }
        System.out.println(sb);

    }


}
