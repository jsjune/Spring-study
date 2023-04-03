package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1085 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int x = Integer.parseInt(str[0]);
        int y = Integer.parseInt(str[1]);
        int w = Integer.parseInt(str[2]);
        int h = Integer.parseInt(str[3]);
        int min = Integer.MAX_VALUE;
        min = Math.min(min, w - x);
        min = Math.min(min, h - y);
        min = Math.min(min, x);
        min = Math.min(min, y);
        System.out.println(min);
    }
}
