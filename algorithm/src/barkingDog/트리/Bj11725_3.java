package barkingDog.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj11725_3 {
    static int n;
    static Node[] list;
    static boolean[] visited;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        parents = new int[n + 1];
        list = new Node[n + 1];
        visited = new boolean[n + 1];

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list[from] = new Node(to, list[from]);
            list[to] = new Node(from, list[to]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        visited[1] = true;
        q.offer(1);
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            for (Node temp = list[cur]; temp != null; temp = temp.next) {
                if (visited[temp.node] == false) {
                    visited[temp.node] = true;
                    parents[temp.node] = cur;
                    q.add(temp.node);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(parents[i]).append("\n");
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
