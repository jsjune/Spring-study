package barkingDog.덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/* AC */
public class Bj5430 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            char[] cmd = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            String[] split = arr.substring(1, arr.length()-1).split(",");
            Deque<String> dq = new LinkedList<>();
            if (n != 0) {
                for (String s : split) {
                    dq.offer(s);
                }
            }
            boolean reverse = true;
            boolean error = false;
            for (int j = 0; j < cmd.length; j++) {
                switch (cmd[j]) {
                    case 'R':
                        reverse = !reverse;
                        break;

                    case 'D':
                        if (!dq.isEmpty()) {
                            String s = reverse ? dq.pollFirst() : dq.pollLast();
                            break;
                        } else {
                            error = true;
                            break;
                        }
                    default:
                        break;
                }
                if (error) {
                    break;
                }
            }
            if (error) {
                sb.append("error").append("\n");
            } else {
                sb.append("[");
                while (!dq.isEmpty()) {
                    sb.append(reverse ? dq.pollFirst() : dq.pollLast());
                    if (dq.size()!=0) {
                        sb.append(",");
                    }
                }
                sb.append("]").append("\n");
            }

        }
        System.out.println(sb);
    }

}
