package barkingDog.수찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 좌표 압축 */
public class Bj18870_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] sortArr = arr.clone();
        Arrays.sort(sortArr);

        int str = 0;
        int[] result = new int[n];
        result[0] = str;
        str++;
        for (int i = 1; i < n; i++) {
            if (sortArr[i] == sortArr[i - 1]) {
                result[i] = result[i - 1];
            } else {
                result[i] = str++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[find(sortArr, arr[i])] + " ");
        }
        System.out.println(sb);
    }

    private static int find(int[] sortArr, int target) {
        int str = 0,end = sortArr.length-1;
        while (str <= end) {
            int mid = (str + end) / 2;
            if (target > sortArr[mid]) {
                str = mid + 1;
            } else {
                end = mid -1;
            }
        }
        return str;
    }
}
