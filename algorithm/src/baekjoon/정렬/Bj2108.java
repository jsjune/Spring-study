package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int average = (int)Math.round((double) Arrays.stream(arr).sum() / N);
        sb.append(average + "\n");

        Arrays.sort(arr);
        int median = arr[N / 2];
        sb.append(median + "\n");

        int mode = 0;
        int[] idx = new int[8001];
        for (int i = 0; i < N; i++) {
            idx[arr[i]+4000]++;
        }
        int max = 0;
        boolean flag = false;
        for (int i = 0; i < idx.length; i++) {
            if (idx[i] > max) {
                max = idx[i];
                mode = i - 4000;
                flag = true;
            } else if (max == idx[i] && flag == true) {
                mode = i - 4000;
                flag = false;
            }
        }
        sb.append(mode + "\n");

        sb.append(arr[N-1]-arr[0]);

        System.out.println(sb);
    }
}
