package barkingDog.배열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Bj1406 {

    static char[] list;
    static int index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        list = br.readLine().toCharArray();

        int n = Integer.parseInt(br.readLine());
        index = list.length;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            if (word.equals("L") && index > 0) {
                index--;
            } else if (word.equals("D") && index < list.length) {
                index++;
            } else if (word.equals("B") && index > 0) {
                char[] clone = list.clone();
                list = new char[list.length - 1];
                for (int j = 0; j < list.length; j++) {
                    if (j >= index - 1) {
                        list[j] = clone[j + 1];
                    } else {
                        list[j] = clone[j];
                    }
                }
                index--;
            } else if (word.equals("P")) {
                char alpa = st.nextToken().charAt(0);
                char[] clone = list.clone();
                list = new char[list.length + 1];
                for (int j = 0; j < list.length; j++) {
                    if (j == index) {
                        list[j] = alpa;
                    } else if (j > index) {
                        list[j] = clone[j-1];
                    } else {
                        list[j] = clone[j];
                    }
                }
                index++;
            }

        }
        for (char c : list) {
            bw.append(c);
        }
        bw.flush();
        bw.close();
    }


}
