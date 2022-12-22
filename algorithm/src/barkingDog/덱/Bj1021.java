package barkingDog.덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* 회전하는 큐 */
public class Bj1021 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            dq.add(i);
        }
        int result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            while (true) {
                int index = 0;
                Iterator<Integer> iter = dq.iterator();
                while (iter.hasNext()) {
                    if (iter.next() == num) {
                        break;
                    }
                    index++;
                }
                if (index == 0) {
                    dq.pollFirst();
                    break;
                } else if (index > dq.size() / 2) {
                    dq.addFirst(dq.pollLast());
                    result++;
                } else {
                    dq.addLast(dq.pollFirst());
                    result++;
                }
            }

        }
        System.out.println(result);
    }
}
