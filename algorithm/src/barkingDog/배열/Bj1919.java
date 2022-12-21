package barkingDog.배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1919 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] alphabet = new int[26];
        for (char c : br.readLine().toCharArray()) {
            alphabet[c - 97]++;
        }
        for (char c : br.readLine().toCharArray()) {
            alphabet[c - 97]--;
        }
        int sum = 0;
        for (int num : alphabet) {
            sum += Math.abs(num);
        }
        System.out.println(sum);
    }

}
