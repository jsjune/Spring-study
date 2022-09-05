package array.solution;

import java.util.Scanner;

public class FibonacciSequence {
    public void solution(int n) {
        int a=1, b=1, c;
        System.out.print(a+" "+b+" ");
        for (int i = 2; i < n; i++) {
            c=a+b;
            System.out.print(c+" ");
            a=b;
            b=c;
        }
    }

    public static void main(String[] args) {
        FibonacciSequence t = new FibonacciSequence();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        t.solution(n);
    }
}
