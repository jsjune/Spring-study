package barkingDog.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 트리의 부모 찾기 */
public class Bj11725 {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];
        int[] parentNode = new int[n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        while (!q.isEmpty()) {
            Integer num = q.poll();
            for (int node : tree.get(num)) {
                if (!visited[node]) {
                    visited[node] = true;
                    q.add(node);
                    parentNode[node] = num;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            System.out.println(parentNode[i]);
        }
    }
}
