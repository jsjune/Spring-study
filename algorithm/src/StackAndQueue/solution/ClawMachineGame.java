package StackAndQueue.solution;

import java.util.Scanner;
import java.util.Stack;

public class ClawMachineGame {
    public int solution(int n, int[][] board, int m, int[] arr) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (board[i][arr[j] - 1] != 0) {
                    int tmp = board[i][arr[j] - 1];
                    board[i][arr[j] - 1] = 0;
                    if (!stack.isEmpty() && stack.peek() == tmp) {
                        answer += 2;
                        stack.pop();
                    } else {
                        stack.push(tmp);
                    }
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        ClawMachineGame t = new ClawMachineGame();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = kb.nextInt();
            }
        }
        int m = kb.nextInt();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(n, board, m, arr));
    }
}
