package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int a = arr[0];
        int b = arr[1];
        int gcd = getGcd(a, b);
        int lcm = a / gcd * b / gcd * gcd;
        System.out.println(gcd + "\n" + lcm);
    }

    static int getGcd(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return getGcd(b, a % b);
        }
    }
}
