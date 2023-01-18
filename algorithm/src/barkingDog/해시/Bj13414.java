package barkingDog.해시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

/* 수강신청 */
public class Bj13414 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (int i = 1; i <= T; i++) {
            String num = br.readLine();
            if (set.contains(num)) {
                set.remove(num);
            }
            set.add(num);
        }
        StringBuilder sb = new StringBuilder();
        for (String answer : set) {
            if (cnt == 0) {
                break;
            }
            sb.append(answer).append("\n");
            cnt--;
        }
        System.out.println(sb);
    }
}
