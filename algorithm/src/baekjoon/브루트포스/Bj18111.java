package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 작업에 걸리는 최소시간
// 답이 여러 개 있다면 그 중에서 땅의 높이가 가장 높은 것 출력
//

public class Bj18111 {
    static int N, M, B;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        int time = Integer.MAX_VALUE;
        int height = 0;
        for (int k = max; k >= min; k--) {
            int t = 0;
            int block = B;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] > k) {
                        t += 2 * (arr[i][j] - k);
                        block += arr[i][j] -k;
                    } else if (arr[i][j] < k) {
                        t += k - arr[i][j];
                        block -= k - arr[i][j];
                    }
                }
            }
            if (block >= 0 && time > t) {
                time = t;
                height = k;
            }
        }
        System.out.println(time + " " + height);
    }
}
