package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj13335 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        Queue<Integer> truck = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck.add(Integer.parseInt(st.nextToken()));
        }
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }
        int time = 0;
        int sum = 0;
        while (!bridge.isEmpty()) {
            time++;
            sum -= bridge.poll();
            if (!truck.isEmpty()) {
                if (truck.peek() + sum <= l) {
                    Integer newTruck = truck.poll();
                    sum += newTruck;
                    bridge.add(newTruck);
                } else {
                    bridge.offer(0);
                }
            }
        }
        System.out.println(time);
    }

}
