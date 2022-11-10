package BFSAndDFS;

/* 입력예제     출력예제
*   3 2         1 1
*   n m         1 2
*               1 3
*               2 1
*               2 2
*               2 3
*               3 1
*               3 2
*               3 3*/

import java.util.Scanner;

public class DuplicatePermutation {
    static int[] pm;
    static int n,m;

    public void DFS(int L) {
        if (L == m) {
            for (int x : pm) {
                System.out.print(x + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= n; i++) {
                pm[L]=i;
                DFS(L + 1);
            }
        }
    }

    public static void main(String[] args) {
        DuplicatePermutation t = new DuplicatePermutation();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        m = kb.nextInt();
        pm = new int[m];
        t.DFS(0);
    }
}
