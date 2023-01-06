package barkingDog.정렬2;

import java.util.Arrays;
import java.util.Scanner;
/* 역원소 정렬 */
public class Bj5648 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Long[] arr = new Long[n];
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            int size = str.length();
            String tmp = "";
            for (int j = size-1; j >= 0; j--) {
                tmp += str.charAt(j);
            }
            arr[i] = Long.parseLong(tmp);
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (long a : arr) {
            sb.append(a + "\n");
        }
        System.out.println(sb);
    }

}
