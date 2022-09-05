package array.solution;

import java.util.Scanner;

public class SieveOfEratosthenes {
    public int solution(int n) {
        int answer = 0;
        int[] ch = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            if (ch[i] == 0) {
                answer++;
                for (int j = i; j <= n; j = j + i) {
                    ch[j] = i;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        SieveOfEratosthenes t = new SieveOfEratosthenes();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        System.out.println(t.solution(n));
    }
}
