package BFSAndDFS.DFS;

import java.util.Scanner;

public class Combinations {
    static int n,m;
    static int[] combi;
    public void DFS(int L,int s) {
        if (L == m) {
            for (int x : combi) {
                System.out.print(x+" ");
            }
            System.out.println();
        } else {
            for (int i = s; i <= n; i++) {
                combi[L] = i;
                DFS(L + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        Combinations t = new Combinations();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        combi = new int[m];
        t.DFS(0, 1);
    }
}
