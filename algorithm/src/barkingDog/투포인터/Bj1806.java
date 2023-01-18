package barkingDog.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 부분 합 */
public class Bj1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int str = 0, end = 0, tot = arr[0];
        while (end < n && str < n) {
            if (tot < s) {
                end++;
                if (end == n) {
                    break;
                }
                tot += arr[end];
            }else {
                tot -= arr[str++];
                min = Math.min(min, end - str + 1);
            }
        }
//        for (str = 0; str < n; str++) {
//            while (end < n && tot < s) {
//                end++;
//                if (end != n) {
//                    tot += arr[end];
//                }
//            }
//            if (end == n) {
//                break;
//            }
//            min = Math.min(min, end - str + 1);
//            tot -= arr[str];
//        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
