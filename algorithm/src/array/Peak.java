package array;

import java.util.Arrays;
import java.util.Scanner;

public class Peak {
    public int solution(int n, int[][] arr) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j] > arr[i - 1][j] && arr[i][j] > arr[i][j + 1] && arr[i][j] > arr[i + 1][j] && arr[i][j] > arr[i][j - 1]) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Peak t = new Peak();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] arr = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            arr[0][i] = 0;
            arr[n+1][i] = 0;
            arr[i][0] = 0;
            arr[n+1][0] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                arr[i][j] = kb.nextInt();
            }
        }
        System.out.println(t.solution(n, arr));
    }
}
