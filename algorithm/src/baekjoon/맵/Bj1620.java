package baekjoon.맵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Bj1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        String[] pokemon = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            map.put(name, i);
            pokemon[i] = name;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (isStringNumber(str)) {
                int idx = Integer.parseInt(str);
                sb.append(pokemon[idx]).append("\n");
            } else {
                sb.append(map.get(str)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean isStringNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
