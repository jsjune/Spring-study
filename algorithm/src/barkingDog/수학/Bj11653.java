package barkingDog.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 소인수 분해 */
public class Bj11653 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int a = 2;
        while (n != 1) {
            if (n % a == 0) {
                sb.append(a).append("\n");
                n /= a;
            } else {
                a++;
            }
        }
        System.out.println(sb);
    }
}
