package barkingDog.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* 결혼식 */
public class Bj5567 {

    static int n, m;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        check = new int[n + 1];
        for (int i = 1; i <=n ; i++) {
            arr.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            arr.get(b).add(a);
        }

        check[1]=1;
        dfs(1, 0);

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (check[i] == 1) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int a, int dep) {
        if (dep == 2) {
            return;
        }
        for (int i : arr.get(a)) {
            check[i] = 1;
            dfs(i, dep + 1);
        }
    }
}
