package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EatOrBeEaten_7795 {
//    public int solution(int aSize, int bSize, int[] arrA, int[] arrB) {
//        int answer = 0, a = 0, b = 0;
//        while (a < aSize) {
//            if (arrA[a] > arrB[b++]) {
//                answer++;
//            }
//            if (b > bSize - 1) {
//                b=0;
//                a++;
//            }
//        }
//        return answer;
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int aSize = 0, bSize = 0;
        int[] arrA, arrB;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            aSize = Integer.parseInt(st.nextToken());
            bSize = Integer.parseInt(st.nextToken());

            arrA = new int[aSize];
            arrB = new int[bSize];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < aSize; j++) {
                arrA[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < bSize; j++) {
                arrB[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arrB);
            int ans = 0;
            for (int j = 0; j < aSize; j++) {
                ans += binarySearch(arrB, arrA[j], 0, bSize - 1);
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    private static int binarySearch(int[] arrB, int target, int start, int end) {
        int cnt = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arrB[mid] < target) {
                start = mid + 1;
                cnt = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return cnt;
    }
}
