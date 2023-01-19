package barkingDog.이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

/* 이중 우선순위 큐 */
public class Bj7762_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- != 0) {
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int n = Integer.parseInt(br.readLine());
            while (n-- != 0) {
                String[] str = br.readLine().split(" ");
                int num = Integer.parseInt(str[1]);
                String alp = str[0];
                switch (alp) {
                    case "I":
                        tm.put(num, tm.getOrDefault(num, 0) + 1);
                        break;
                    case "D":
                        if (!tm.isEmpty()) {
                            Integer number = num == 1 ? tm.lastKey() : tm.firstKey();
                            if (tm.put(number, tm.get(number) - 1) == 1) {
                                tm.remove(number);
                            }
                        }
                        break;
                }
            }
            sb.append(tm.isEmpty() ? "EMPTY\n" : tm.lastKey() + " " + tm.firstKey() + "\n");
        }
        System.out.println(sb);
    }
}