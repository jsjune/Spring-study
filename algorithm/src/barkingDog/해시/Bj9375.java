package barkingDog.해시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/* 패션왕 신해빈 */
public class Bj9375 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            for (int j = 0; j < n; j++) {
                String[] str = br.readLine().split(" ");
                map.put(str[1], map.getOrDefault(str[1], 0) + 1);
            }
            int result = 1;
            for (Integer value : map.values()) {
                result *= value+1;
            }
            sb.append(result - 1).append("\n");
        }
        System.out.println(sb);
    }
}
