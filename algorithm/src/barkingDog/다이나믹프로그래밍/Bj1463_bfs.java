package barkingDog.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import org.w3c.dom.Node;

/* 1로 만들기 */
public class Bj1463_bfs {

    static int n;
    static Queue<Node> q;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        check = new int[n + 1];
        q = new LinkedList<>();
        q.offer(new Node(n, 0));
        System.out.println(bfs());
    }

    private static int bfs() {
        while (!q.isEmpty()) {
            Node node = q.poll();
            int num = node.num;
            if (num == 1) {
                return node.cnt;
            }
            if (num > 1 && check[num] != 1) {
                int cnt = node.cnt + 1;
                check[num] = 1;
                if (num % 3 == 0) {
                    q.add(new Node(num / 3, cnt));
                }
                if (num % 2 == 0) {
                    q.add(new Node(num / 2, cnt));
                }
                q.add(new Node(num - 1, cnt));
            }
        }
        return -1;
    }

    static class Node {

        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

}
