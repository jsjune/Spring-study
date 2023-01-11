package barkingDog.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/* 회의실 배정 */
public class Bj1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Conference[] arr = new Conference[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i] = new Conference(start, end);
        }
        Arrays.sort(arr);
        int cnt = 0;
        int endTime = 0;
        for (int i = 0; i < n; i++) {
            if (endTime <= arr[i].start) {
                endTime = arr[i].end;
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static class Conference implements Comparable<Conference> {
        int start;
        int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Conference o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }
}
