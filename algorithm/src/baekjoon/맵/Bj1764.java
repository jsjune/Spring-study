package baekjoon.맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < n + m; i++) {
            String name = br.readLine();
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        ArrayList<String> arr = new ArrayList<>();
        for (String s : map.keySet()) {
            if (map.get(s) > 1) {
                arr.add(s);
            }
        }
        Collections.sort(arr);
        System.out.println(arr.size());
        arr.forEach(i-> System.out.println(i));
    }
}
