import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] arr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        DFS(0);
        System.out.println(sb);
    }

    private static void DFS(int L) {
        if (L == M) {
            for (int x : arr) {
                sb.append(x + " ");
            }
            sb.append("\n");
        } else {
            for (int i = 1; i <= N; i++) {
                arr[L] = i;
                DFS(L + 1);
            }
        }
    }
}
