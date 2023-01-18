package barkingDog.해시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeMap;

/* 회사에 있는 사람 */
public class Bj7785 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeMap<String, String> map = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            map.put(str[0], str[1]);
        }
        StringBuilder sb = new StringBuilder();
        for (String s : map.keySet()) {
            if (map.get(s).equals("enter")) {
                sb.append(s + "\n");
            }
        }
        System.out.println(sb);
    }

}
