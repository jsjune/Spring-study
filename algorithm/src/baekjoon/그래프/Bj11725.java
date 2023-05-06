package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N + 1];
        boolean[] check = new boolean[N + 1];
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from] = new Node(to, nodes[from]);
            nodes[to] = new Node(from, nodes[to]);
        }

        int[] answer = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        check[1] = true;
        q.offer(1);
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            for (Node i = nodes[cur]; i != null; i = i.next) {
                if (check[i.node] == false) {
                    check[i.node] = true;
                    answer[i.node] = cur;
                    q.add(i.node);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < answer.length; i++) {
            sb.append(answer[i] + "\n");
        }
        System.out.println(sb);
    }

    private static class Node {
        int node;
        Node next;

        public Node(int node, Node next) {
            this.node = node;
            this.next = next;
        }
    }
}
