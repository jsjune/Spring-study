package BFSAndDFS;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ExchangeCoins {

    static int n, m, answer = Integer.MAX_VALUE;

    public void DFS(int L, int sum, Integer[] arr) {
        if (sum > m) {
            return;
        }
        if (L >= answer) {
            return;
        }
        if (sum == m) {
            answer = Math.min(L, answer);
        } else {
            for (int i = 0; i < n; i++) {
                DFS(L + 1, sum + arr[i], arr);
            }
        }
    }

    public static void main(String[] args) {
        ExchangeCoins t = new ExchangeCoins();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = kb.nextInt();
        Arrays.sort(arr, Collections.reverseOrder());
        m = kb.nextInt();
        t.DFS(0, 0, arr);
        System.out.println(answer);
    }
}
