package barkingDog.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 공주님의 정원 */
public class Bj2457 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Flower[] arr = new Flower[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int starM = Integer.parseInt(st.nextToken());
            int starD = Integer.parseInt(st.nextToken());
            int endM = Integer.parseInt(st.nextToken());
            int endD = Integer.parseInt(st.nextToken());
            arr[i] = new Flower(starM * 100 + starD, endM * 100 + endD);
        }
        int cnt = 0;
        int start = 301;
        int end = 1201;
        int tmp = start;
        int last = 0;
        while (last < end) {
            for (int i = 0; i < n; i++) {
                if (tmp >= arr[i].start && arr[i].end > last) {
                    last = arr[i].end;
                }
            }
            if (tmp == last) {
                cnt = 0;
                break;
            }
            tmp = last;
            cnt++;
        }
        System.out.println(cnt);
    }

    private static class Flower {
        int start;
        int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }
}
