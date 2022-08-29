package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class FlipSpecificChar {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        char[] chars = input.toCharArray();
        ArrayList<String> extract = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if ((chars[i] >= 65 && chars[i] <= 90) || (chars[i] >= 97 && chars[i] <= 122)) {
                extract.add(String.valueOf(chars[i]));
            }
        }
        Collections.reverse(extract);
        String result = "";
        int cnt = 0;
        for (int i = 0; i < chars.length; i++) {
            if ((chars[i] >= 65 && chars[i] <= 90) || (chars[i] >= 97 && chars[i] <= 122)) {
                result += extract.get(cnt);
                cnt++;
            } else {
                result += chars[i];
            }
        }
        System.out.println(result);
    }
}
