package barkingDog.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/* 잃어버린 괄호 */
public class Bj1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sub = br.readLine().split("-");
        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < sub.length; i++) {
            int tmp = 0;
            String[] add = sub[i].split("\\+");
            for (int j = 0; j < add.length; j++) {
                tmp += Integer.parseInt(add[j]);
            }
            if (sum == -1) {
                sum = tmp;
            } else {
                sum -= tmp;
            }
        }
        System.out.println(sum);
    }
}
