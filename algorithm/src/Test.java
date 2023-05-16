import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Test {
    private static Node root;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        root = new Node(1);
        root.lt = new Node(2);
        root.rt = new Node(3);
        root.lt.lt = new Node(4);
        root.lt.rt = new Node(5);
        root.rt.lt = new Node(6);
        root.rt.rt = new Node(7);
        BFS(root);
        System.out.println(sb);
    }

    private static void BFS(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int L  = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            System.out.print(L + " : ");
            for (int i = 0; i < len; i++) {
                Node cur = q.poll();
                System.out.print(cur.data+ " ");
                sb.append(cur.data + " ");
                if (cur.lt != null) {
                    q.add(cur.lt);
                }
                if (cur.rt != null) {
                    q.add(cur.rt);
                }
            }
            L++;
            System.out.println();
        }
    }

    private static class Node {
        int data;
        Node lt;
        Node rt;

        public Node(int data) {
            this.data = data;
            this.lt = this.rt = null;
        }
    }

}
