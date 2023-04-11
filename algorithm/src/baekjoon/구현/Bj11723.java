package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            int num = 0;
            if (str[0].equals("add")) {
                num = Integer.parseInt(str[1]);
                if (!set.contains(num)) {
                    set.add(num);
                }
            } else if (str[0].equals("remove")) {
                num = Integer.parseInt(str[1]);
                if (set.contains(num)) {
                    set.remove(num);
                }
            } else if (str[0].equals("check")) {
                num = Integer.parseInt(str[1]);
                if (set.contains(num)) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (str[0].equals("toggle")) {
                num = Integer.parseInt(str[1]);
                if (set.contains(num)) {
                    set.remove(num);
                } else {
                    set.add(num);
                }
            } else if (str[0].equals("all")) {
                set.clear();
                set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 18, 20));
            } else if (str[0].equals("empty")) {
                set.clear();
            }
        }
        System.out.println(sb);
    }
}
