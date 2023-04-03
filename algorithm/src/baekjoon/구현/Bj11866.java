package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }
        ArrayList<Integer> arr = new ArrayList<>();
        while (!queue.isEmpty()) {
            for (int i = 1; i <= k; i++) {
                if (i == k) {
                    arr.add(queue.poll());
                } else {
                    Integer poll = queue.poll();
                    queue.add(poll);
                }
            }
        }
        String answer = "<";
        for (int i = 0; i < arr.size(); i++) {
            if (i == arr.size() - 1) {
                answer += arr.get(i) + ">";
            } else {
                answer += arr.get(i) + ", ";
            }
        }
        System.out.println(answer);
    }
}
