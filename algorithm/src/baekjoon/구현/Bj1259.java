package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String[] org = br.readLine().split("");
            if (org[0].equals("0")) {
                break;
            }
            String[] tmp = org.clone();
            for (int i = 0; i < org.length; i++) {
                tmp[i] = org[org.length - i - 1];
            }
            boolean check = true;
            for (int i = 0; i < org.length; i++) {
                if (!org[i].equals(tmp[i])) {
                    check = false;
                }
            }
            if (check) {
                sb.append("yes" + "\n");
            } else {
                sb.append("no" + "\n");
            }
        }
        System.out.println(sb);
    }
}
