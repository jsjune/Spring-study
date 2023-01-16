package barkingDog.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 수 고르기 */
public class Bj2230 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int str = 0,end = 0;
        int min = Integer.MAX_VALUE;
        while (end < n && str<n) {
            if (arr[end] - arr[str] < m) {
                end++;
                continue;
            }
            if (arr[end] - arr[str] == m) {
                min = m;
            }
            min = Math.min(min, arr[end] - arr[str]);
            str++;
        }
        System.out.println(min);

    }

}
