package recursiveTreeGraph;

import java.util.Scanner;

/*입력예제   출력예제
* 5 9       6
* 1 2
* 1 3
* 1 4
* 2 1
* 2 3
* 2 5
* 3 4
* 4 2
* 4 5
* */

public class PathSearchDFS {
    static int n,m,answer=0;
    static int[][] graph;
    static int[] ch;

    public void DFS(int v) {
        if (v == n) answer++;
        else {
            for (int i = 1; i <= n; i++) {
                if (graph[v][i] == 1 && ch[i] == 0) {
                    ch[i]=1;
                    DFS(i);
                    ch[i]=0;
                }
            }
        }
    }

    public static void main(String[] args) {
        PathSearchDFS t = new PathSearchDFS();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt(); //노드의 갯수, 정점의 갯수
        m = kb.nextInt(); //간선의 갯수
        graph = new int[n + 1][n + 1];
        ch = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            graph[a][b]=1;
        }
        ch[1]=1; //출발점
        t.DFS(1);
        System.out.println(answer);
    }
}
