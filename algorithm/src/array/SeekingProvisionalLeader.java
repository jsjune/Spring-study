package array;

import java.util.Arrays;
import java.util.Scanner;

public class SeekingProvisionalLeader {
    public int solution(int n, int[][] arr) {
        int[] students = new int[n];
        int num;
        for (int i = 0; i < n - 1; i++) {
            num=0;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (arr[i][j] == arr[j][k]) {
                        num++;
                    }
                }
            }
            students[i] = num;
        }
        System.out.println(Arrays.toString(students));
        return 0;
    }

    public static void main(String[] args) {
        SeekingProvisionalLeader t = new SeekingProvisionalLeader();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = kb.nextInt();
            }
        }
        System.out.println(t.solution(n, arr));
    }
}
