package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Bj1676 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BigInteger answer = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            answer = answer.multiply(BigInteger.valueOf(i));
        }
        int a = solution(0, answer);
        System.out.println(a);
    }

    private static int solution(int cnt, BigInteger answer) {
        BigInteger remainder = answer.remainder(BigInteger.TEN);
        if (remainder.equals(BigInteger.ZERO)) {
            BigInteger divide = answer.divide(BigInteger.TEN);
            return solution(cnt + 1, divide);
        } else {
            return cnt;
        }
    }


}
