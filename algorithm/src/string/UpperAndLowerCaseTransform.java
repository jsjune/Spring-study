package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpperAndLowerCaseTransform {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String upperCase = input.toUpperCase();
        String lowerCase = input.toLowerCase();
        String result = "";

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == upperCase.charAt(i)) {
                result += lowerCase.charAt(i);
            }
            if (input.charAt(i) == lowerCase.charAt(i)) {
                result += upperCase.charAt(i);
            }
        }
        System.out.println(result);
    }
}
