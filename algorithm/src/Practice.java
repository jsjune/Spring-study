import java.util.Collections;
import java.util.PriorityQueue;

public class Practice {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(60);
        pq.add(30);
        pq.add(9);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        System.out.println(pq.peek());
    }
}
