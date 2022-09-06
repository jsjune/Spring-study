package array;

import java.util.ArrayList;
import java.util.Scanner;

public class InvertedPrimeNumber {
    public ArrayList<Integer> solution(int n, String[] list) {
        int[] checkPrimeNumber = new int[n];
        for (int i = 0; i < n; i++) {
            String reverse = new StringBuilder(list[i]).reverse().toString();
            checkPrimeNumber[i] = Integer.parseInt(reverse);
        }
        int[] primeNumber = new int[100001];
        for (int i = 2; i <= primeNumber.length - 1; i++) {
            primeNumber[i] = i;
        }
        for (int i = 2; i <= primeNumber.length - 1; i++) {
            for (int j = i + i; j <= primeNumber.length - 1; j += i) {
                primeNumber[j] = 0;
            }
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for (int num : checkPrimeNumber) {
            for (int i = 0; i < primeNumber.length; i++) {
                if (primeNumber[i] != 0 && primeNumber[i] == num) {
                    answer.add(num);
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        InvertedPrimeNumber t = new InvertedPrimeNumber();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        String[] list = new String[n];
        for (int i = 0; i < n; i++) {
            list[i] = kb.next();
        }
        for (int answer : t.solution(n, list)) {
            System.out.print(answer + " ");
        }
    }
}
