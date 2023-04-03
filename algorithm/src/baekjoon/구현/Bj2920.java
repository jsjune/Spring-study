package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj2920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> org = Arrays.asList(br.readLine().split(" "));
        List<String> tmp = new ArrayList<>();
        tmp.addAll(org);
        tmp.sort(Comparator.naturalOrder());
        if (Arrays.equals(org.toArray(),tmp.toArray())) {
            System.out.println("ascending");
        } else {
            tmp.sort(Comparator.reverseOrder());
            if (Arrays.equals(org.toArray(),tmp.toArray())) {
                System.out.println("descending");
            } else {
                System.out.println("mixed");
            }
        }
    }
}
