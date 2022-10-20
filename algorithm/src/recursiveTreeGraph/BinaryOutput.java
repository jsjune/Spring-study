package recursiveTreeGraph;

/*입력 설명 : 첫 번째 줄에 10진수 N(1<=N<=1000)이 주어집니다.
 * 출력 설명 : 첫 번째 줄에 이진수를 출력하세요
 * 입력 예제 : 11
 * 출력 예제 : 1011
 * */

public class BinaryOutput {
    public void DFS(int n) {
        if (n == 0) {
            return;
        } else {
            DFS(n/2);
            System.out.print(n%2+"");
        }
    }
    public static void main(String[] args) {
        BinaryOutput t = new BinaryOutput();
        t.DFS(11);
    }
}
