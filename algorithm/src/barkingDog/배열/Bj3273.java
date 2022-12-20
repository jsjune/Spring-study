package barkingDog.배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/* 두 수의 합 */
public class Bj3273 {

    public int solution(int[] arr, int x) {
        Arrays.sort(arr);
        int lt = 0;
        int rt = arr.length-1;
        int cnt = 0;
        int sum = 0;

        while (lt < rt) {
            sum = arr[lt] + arr[rt];
            if (sum == x) {
                cnt++;
            }
            if (sum <= x) {
                lt++;
            } else {
                rt--;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        Bj3273 t = new Bj3273();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());
        System.out.println(t.solution(arr, x));
    }

}
