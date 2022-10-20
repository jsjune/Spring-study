package recursiveTreeGraph;

/* 문제 설명 : 피보나치 수열을 출력한다. 피보나치 수열이란 앞의 2개의 수르 합하여 다음 숫자가 되는 수열이다.
 * 입력은 피보나치 수열의 총 항의 수 이다. 만약 7이 입력되면 1 1 2 3 5 8 13을 출력하면된다.
 * 입력 설명 : 첫 번째 줄에 총 함수 N(3<=N<=45)이 입력된다.
 * 출력 설명 : 첫 번째 줄에 피보나치 수열을 출력합니다.
 * 입력 예제 : 10
 * 출력 예제 : 1 1 2 3 5 8 13 21 34 55
 * */

public class FibonacciRecursion {
    public int DFS(int n) {
        if(n==1) return 1;
        else if (n == 2) return 1;
        else return DFS(n-2)+DFS(n-1);
    }
    public static void main(String[] args) {
        FibonacciRecursion t = new FibonacciRecursion();
        int n=10;
        for(int i=1;i<=n;i++) System.out.print(t.DFS(i)+" ");
    }
}
