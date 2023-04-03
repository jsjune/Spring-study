package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj4153 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] arr;
        while (true) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
            if (arr[0] == 0) {
                break;
            }
            int a = arr[0];
            int b = arr[1];
            int c = arr[2];
            if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
                sb.append("right").append("\n");
            } else {
                sb.append("wrong").append("\n");
            }
        }
        System.out.println(sb);
    }
}
