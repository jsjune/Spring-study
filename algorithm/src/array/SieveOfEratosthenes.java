package array;

import java.util.Scanner;

public class SieveOfEratosthenes {
    public int solution(int n) {
        int answer = 0;
        int[] list = new int[n+1];
        for (int i = 2; i <= n; i++) {
            list[i]=i;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i + i; j <= n; j += i) {
                list[j]=0;
            }
        }
        for (int i = 0; i < n; i++) {
            if (list[i] != 0) {
                answer++;
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
