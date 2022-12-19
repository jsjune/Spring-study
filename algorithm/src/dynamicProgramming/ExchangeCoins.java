package dynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class ExchangeCoins {
    static int n, m;
    static int[] dy;

    public int solution(int[] coin) {
        Arrays.fill(dy, Integer.MAX_VALUE);
        dy[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <=m ; j++) {
                dy[j] = Math.min(dy[j], dy[j - coin[i]] + 1);
            }
        }
        return dy[m];
    }

    public static void main(String[] args) {
        ExchangeCoins t = new ExchangeCoins();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        m = kb.nextInt();
        dy = new int[m + 1];
        System.out.println(t.solution(arr));
    }

}
