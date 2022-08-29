package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordFlip {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            String flipWord = "";
            for (int j = input.length()-1; j >= 0; j--) {
                flipWord += input.charAt(j);
            }
            result.add(flipWord);
        }
        for (String word : result) {
            System.out.println(word);
        }
    }
}
