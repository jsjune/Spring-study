package barkingDog.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/* 카드 */
public class Bj11652 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Long, Integer> map = new HashMap<>();
        int max = 0;
        long target = 0;
        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            map.put(num, map.getOrDefault(num, 0) + 1);

            if (map.get(num) > max) {
                max = map.get(num);
                target = num;
            } else if (map.get(num) == max) {
                target = Math.min(target, num);
            }
        }
        System.out.println(target);

    }
}
