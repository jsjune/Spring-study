package recursiveTreeGraph;

/*입력 설명 : 첫 번째 줄은 정수 N(3<=N<=10)이 입력된다.
 * 출력 설명 : 첫째 줄에 출력한다.
 * 입력 예제 : 3
 * 출력 예제 : 1 2 3*/

public class RecursiveFunction {
    public void DFS(int n) {
        if (n == 0) {
            return;
        } else {
            System.out.print(n + " ");
            DFS(n - 1);
            System.out.print(n + " ");
        }
    }

    public static void main(String[] args) {
        RecursiveFunction t = new RecursiveFunction();
        t.DFS(3);
    }
}
