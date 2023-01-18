package barkingDog.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 소수의 연속합 */
public class Bj1644 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        arr[0] = arr[1] = 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            if (arr[i] == 0) {
                for (int j = i * i; j <= n; j += i) {
                    arr[j] = 1;
                }
            }
        }
        for (int i = 0; i <= n; i++) {
            if (arr[i] == 0) {
                list.add(i);
            }
        }
        int str = 0, end = 0, tot = 0, cnt = 0;
        while (true) {
            if (tot >= n) {
                tot -= list.get(str++);
            } else if (end==list.size()) {
                break;
            } else {
                tot += list.get(end++);
            }
            if (tot == n) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

}
