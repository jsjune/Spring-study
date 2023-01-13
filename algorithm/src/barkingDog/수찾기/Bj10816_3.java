package barkingDog.수찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/* 숫자 카드 2 */
public class Bj10816_3 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(upperBound(n, target) - lowerBound(n, target) + " ");
        }
        System.out.println(sb);
    }

    private static int upperBound(int n, int target) {
        int str = 0, end = n-1;
        while (str <= end) {
            int mid = (str + end) / 2;
            if (arr[mid] > target) {
                end = mid - 1;
            } else {
                str = mid + 1;
            }
        }
        return str;
    }

    private static int lowerBound(int n, int target) {
        int str = 0, end = n-1;
        while (str <= end) {
            int mid = (str + end) / 2;
            if (arr[mid] >= target) {
                end = mid - 1;
            } else {
                str = mid + 1;
            }
        }
        return str;
    }

}
