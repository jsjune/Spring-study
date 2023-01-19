package barkingDog.이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/* 이중 우선순위 큐 */
public class Bj7662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- != 0) {
            int n = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minQue = new PriorityQueue<>();
            PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());
            while (n-- != 0) {
                String[] str = br.readLine().split(" ");
                String alp = str[0];
                int num = Integer.parseInt(str[1]);
                if (alp.equals("I")) {
                    minQue.add(num);
                    maxQue.add(num);
                } else if (alp.equals("D") && num == 1) {
                    if (!maxQue.isEmpty()) {
                        Integer max = maxQue.poll();
                        minQue.remove(max);
                    }
                } else if (alp.equals("D") && num == -1) {
                    if (!minQue.isEmpty()) {
                        Integer min = minQue.poll();
                        maxQue.remove(min);
                    }
                }
            }
            if (minQue.isEmpty() || maxQue.isEmpty()) {
                sb.append("EMPTY" + "\n");
            } else {
                sb.append(maxQue.peek() + " " + minQue.peek());
            }
        }
        System.out.println(sb);
    }
}
