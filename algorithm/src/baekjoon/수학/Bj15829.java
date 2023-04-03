package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj15829 {
    static final int M = 1234567891;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();
        long sum = 0l;
        long pow = 1l;
        for (int i = 0; i < L; i++) {
            int num =  str.charAt(i) - 'a' + 1;
            sum += num * pow % M;
            pow = pow * 31 % M;
        }
        long result = sum % M;
        System.out.println(result);
    }
}
