package baekjoon.bfsAndDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* 촌수계산 */
public class Bj2644 {

    static int ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int findA = Integer.parseInt(st.nextToken());
        int findB = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        boolean[] visited = new boolean[n + 1];
        dfs(arr, visited, findA, findB, 0);
        System.out.println(ans);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> arr, boolean[] visited, int findA,
        int findB, int cnt) {
        visited[findA] = true;
        for (Integer i : arr.get(findA)) {
            if (!visited[i]) {
                if (i == findB) {
                    ans = cnt + 1;
                    return;
                }
                dfs(arr, visited, i, findB, cnt + 1);
            }
        }
    }

}
