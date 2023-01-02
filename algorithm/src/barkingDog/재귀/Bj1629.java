package barkingDog.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 곱셈 */
/* 모듈러 합동 공식 */
public class Bj1629 {


    private static long recursion(long a, int b, int c) {
        if (b == 1) {
            return a % c;
        } else if (b % 2 == 0) {
            long tmp = recursion(a, b / 2, c);
            return tmp * tmp % c;
        } else {
            long tmp = recursion(a, b / 2, c);
            return ((tmp * tmp % c) * a) % c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(recursion(a, b, c));
    }
}
