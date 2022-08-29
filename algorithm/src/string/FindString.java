package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FindString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input1 = br.readLine();
        String input2 = br.readLine();

        String test = input1.toLowerCase();
        char lowerCaseInput2 = input2.toLowerCase().charAt(0);
        char[] testCharacterization = test.toCharArray();
        int cnt = 0;
        for (char alphabet : testCharacterization) {
            if (alphabet == lowerCaseInput2) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
