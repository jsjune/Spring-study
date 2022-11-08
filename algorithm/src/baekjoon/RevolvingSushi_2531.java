package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class RevolvingSushi_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + k];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = N; i < N + k; i++) {
            arr[i] = arr[i - N];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int cnt = 0;
        for (int rt = 0; rt < N + k; rt++) {
            cnt++;
            map.put(arr[rt], map.getOrDefault(arr[rt], 0) + 1);
            if (cnt >= k) {
                max = Math.max(max, map.size());
                map.put(arr[rt - k + 1], map.get(arr[rt - k + 1]) - 1);
                if (map.get(arr[rt - k + 1]) == 0) map.remove(arr[rt - k + 1]);

            }
        }
        System.out.println(max);
    }
}
