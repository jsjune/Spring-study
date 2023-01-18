package barkingDog.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 수들의 합 2 */
public class Bj2003 {

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
        int str = 0, end = 0, tot = 0, cnt = 0;
        while (true) {
            if (tot >= m) {
                tot -= arr[str++];
            } else if (end == n) {
                break;
            }else{
                tot += arr[end++];
            }
            if (tot == m) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}
