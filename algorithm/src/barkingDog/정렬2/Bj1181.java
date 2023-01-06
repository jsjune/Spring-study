package barkingDog.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/* 단어 정렬 */
public class Bj1181 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return o1.length()-o2.length();
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0] + "\n");
        for (int i = 1; i < n; i++) {
            if (!arr[i - 1].equals(arr[i])) {
                sb.append(arr[i] + "\n");
            }
        }
        System.out.println(sb);
    }
}
