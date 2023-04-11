package baekjoon.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        int start = 0;
        int end = max;
        while (start < end) {
            int mid = (start + end) / 2;
            long height = 0;
            for (int treeHeight : arr) {
                if (treeHeight > mid) {
                    height += treeHeight - mid;
                }
            }

            if (height < M) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start - 1);
    }
}
