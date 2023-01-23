package barkingDog.우선순위큐;

import java.util.PriorityQueue;

public class Test {

    public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(-1);
        q.add(4);
        q.add(3);
        for (int i = 0; i < 3; i++) {
            Integer poll = q.poll();
            System.out.println(poll);
        }
    }

}
