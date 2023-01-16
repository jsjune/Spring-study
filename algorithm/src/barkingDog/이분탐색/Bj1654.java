package barkingDog.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 랜선 자르기 */
public class Bj1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }
        long min = 1;
        while (min <= max) {
            long mid = (min + max) / 2;
            long cnt = 0;
            for (int i = 0; i < n; i++) {
                cnt += (arr[i] / mid);
            }
            if (cnt < k) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min-1);
    }
}
