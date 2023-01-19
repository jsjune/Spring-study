package barkingDog.이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Bj7762_3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- != 0) {
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int n = Integer.parseInt(br.readLine());
            while (n-- != 0) {
                String[] str = br.readLine().split(" ");
                String alp = str[0];
                int num = Integer.parseInt(str[1]);
                if (alp.equals("I")) {
                    tm.put(num, tm.getOrDefault(num, 0) + 1);
                } else {
                    if (!tm.isEmpty()) {
                        Integer number = num == 1 ? tm.lastKey() : tm.firstKey();
                        if (tm.get(number) == 1) {
                            tm.remove(number);
                        } else if (tm.get(number) > 1) {
                            tm.put(number, tm.get(number) - 1);

                        }

                    }
                }
            }
            sb.append(tm.isEmpty() ? "EMPTY\n" : tm.lastKey() + " " + tm.firstKey() + "\n");
        }
        System.out.println(sb);
    }

}
