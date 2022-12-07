package Greedy;

import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/* 최소스패닝트리 : 프림 */

class Edge3 implements Comparable<Edge3> {
    public int vex;
    public int cost;

    public Edge3(int vex, int cost) {
        this.vex = vex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge3 o) {
        return this.cost - o.cost;
    }
}

public class WonderlandPriorityQueue {

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int m = kb.nextInt();
        ArrayList<ArrayList<Edge3>> graph = new ArrayList<ArrayList<Edge3>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge3>());
        }
        int[] ch = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            int c = kb.nextInt();
            // 무방향 방향 2개를 고려해야된다.
            graph.get(a).add(new Edge3(b, c));
            graph.get(b).add(new Edge3(a, c));
        }
        int answer = 0;
        PriorityQueue<Edge3> pQ = new PriorityQueue<>();
        pQ.offer(new Edge3(1, 0));
        while (!pQ.isEmpty()) {
            Edge3 tmp = pQ.poll();
            int ev = tmp.vex;
            if (ch[ev] == 0) {
                ch[ev] = 1;
                answer += tmp.cost;
                for (Edge3 ob : graph.get(ev)) {
                    if (ch[ob.vex] == 0) {
                        pQ.offer(new Edge3(ob.vex, ob.cost));
                    }
                }
            }
        }
        System.out.println(answer);
    }

}
