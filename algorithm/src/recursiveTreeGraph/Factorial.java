package recursiveTreeGraph;

/* 입력 설명 : 첫 번째 줄에 10진수 N(1<=N<=1000)이 주어집니다.
 * 출력 설명 : 첫 번째 줄에 N팩토리얼 값을 출력합니다.
 * 입력 예제 : 5
 * 출력 예제 : 120
 * */

public class Factorial {
    public int DFS(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * DFS(n - 1);
        }
    }

    public static void main(String[] args) {
        Factorial t = new Factorial();
        System.out.println(t.DFS(5));
    }
}
