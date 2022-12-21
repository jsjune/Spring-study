package barkingDog.배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj11328 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] alphabet = new int[26];
            char[] word1 = st.nextToken().toCharArray();
            char[] word2 = st.nextToken().toCharArray();
            for (char c : word1) {
                alphabet[c - 97]++;
            }
            for (char c : word2) {
                alphabet[c - 97]--;
            }
            boolean check = true;
            for (int num : alphabet) {
                if (num != 0) {
                    check = false;
                }
            }
            System.out.println(check ? "Possible" : "Impossible");
        }

    }

}
