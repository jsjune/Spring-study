import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test {
    private static ArrayList<Integer>[] arr;
    private static boolean[] ch;
    private static int[] dis;
    private static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ch = new boolean[N + 1];
        dis = new int[N + 1];
        arr = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
        }
        BFS(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(i + " : " + dis[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void BFS(int v) {
        ch[v] = true;
        dis[v] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        while (!q.isEmpty()) {
            Integer cv = q.poll();
            for (int nv : arr[cv]) {
                if (!ch[nv]) {
                    ch[nv] = true;
                    q.add(nv);
                    dis[nv] = dis[cv] + 1;
                }
            }
        }
    }
}
