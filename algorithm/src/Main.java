import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    private static int[][] board, dis;
    private static int N, M;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];
        dis = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            char[] numbers = br.readLine().toCharArray();
            for (int j = 1; j <= M; j++) {
                board[i][j] = numbers[j-1] - 48;
            }
        }
        dis[1][1]=1;
        BFS(1, 1);
        System.out.println(dis[N][M]);
    }

    private static void BFS(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        while (!q.isEmpty()) {
            Node tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx >= 1 && nx <= N && ny >= 1 && ny <= M && board[nx][ny] == 1) {
                    board[nx][ny] = 0;
                    q.add(new Node(nx, ny));
                    dis[nx][ny] = dis[tmp.x][tmp.y] + 1;
                }
            }
        }
    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

