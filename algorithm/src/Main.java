import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[] arr, answer, ch;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        ch = new int[N];
        M = Integer.parseInt(st.nextToken());
        answer = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        DFS(0);
        System.out.println(sb);
    }

    private static void DFS(int L) {
        if (L == M) {
            for (int x : answer) {
                sb.append(x + " ");
            }
            sb.append("\n");
        } else {
            for (int i = 0; i < N; i++) {
                if (ch[i]==0) {
                    ch[i] = 1;
                    answer[L] = arr[i];
                    DFS(L + 1);
                    ch[i] = 0;
                }
            }
        }
    }
}

