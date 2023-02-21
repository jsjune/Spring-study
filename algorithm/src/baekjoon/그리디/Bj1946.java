package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/* 신입사원 */
public class Bj1946 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Member> arr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr.add(new Member(a, b));
            }
            Collections.sort(arr);
            int cnt = 1;
            int tmp = arr.get(0).b;
            for (Member member : arr) {
                if (tmp > member.b) {
                    cnt++;
                    tmp = member.b;
                }
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);

    }

    private static class Member implements Comparable<Member> {

        int a;
        int b;

        public Member(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Member o) {
            return this.a - o.a;
        }
    }

}
