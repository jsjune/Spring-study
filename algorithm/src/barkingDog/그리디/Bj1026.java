package barkingDog.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/* 보물 */
public class Bj1026 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input1 = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");
        Integer[] arr1 = new Integer[n];
        Integer[] arr2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(input1[i]);
            arr2[i] = Integer.parseInt(input2[i]);
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2, Collections.reverseOrder());
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr1[i] * arr2[i];
        }
        System.out.println(sum);
    }
}
