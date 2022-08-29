package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordInSentence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] s = input.split(" ");
        String result = "";
        int max = 0;
        for (String s1 : s) {
            if (s1.length() > max) {
                max = s1.length();
                result = s1;
            }
        }
        System.out.println(result);
    }
}
