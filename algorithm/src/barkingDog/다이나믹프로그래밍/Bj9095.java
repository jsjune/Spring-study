package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 1,2,3 더하기 */
public class Bj9095 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dy = new int[11];
        dy[1]=1;
        dy[2]=2;
        dy[3]=4;
        for (int i = 4; i < 11; i++) {
            dy[i] = dy[i - 1] + dy[i - 2] + dy[i - 3];
        }
        for (int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dy[num]).append("\n");
        }
        System.out.println(sb);
    }


}
