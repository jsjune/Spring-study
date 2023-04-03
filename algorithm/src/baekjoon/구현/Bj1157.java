package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toUpperCase();
        int[] alpabet = new int[26];
        for (int i = 0; i < str.length(); i++) {
            alpabet[str.charAt(i)-'A']++;
        }
        int max = -1;
        char answer = ' ';
        for (int i = 0; i < alpabet.length; i++) {
            if (alpabet[i] > max) {
                max = alpabet[i];
                answer = (char) (i + 65);
            } else if(alpabet[i] == max) {
                answer = '?';
            }
        }
        System.out.println(answer);
    }
}
