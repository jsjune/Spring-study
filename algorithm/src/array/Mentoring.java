package array;

import java.util.Arrays;
import java.util.Scanner;

public class Mentoring {
    public int solution(int studentCnt,int testResult, int[][] arr) {
        int answer = 0;
        for (int i = 0; i < testResult; i++) {
            for (int j = 0; j < studentCnt; j++) {

            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Mentoring t = new Mentoring();
        Scanner kb = new Scanner(System.in);
        int studentCnt = kb.nextInt();
        int testResult = kb.nextInt();
        int[][] arr = new int[testResult][studentCnt];
        for (int i = 0; i < testResult; i++) {
            for (int j = 0; j < studentCnt; j++) {
                arr[i][j] = kb.nextInt();
            }
        }
        System.out.println(Arrays.deepToString(arr));
        System.out.println(t.solution(studentCnt,testResult,arr));
    }
}
