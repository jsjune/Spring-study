package barkingDog.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 트리 순회 */
public class Bj1991 {

    public static Node root;
    static StringBuilder sb = new StringBuilder();

    // 전위순회 Preorder : Root -> Left -> Right
    public static void preOrder(Node node) {
        if (node != null) {
            sb.append(node.data);
            if (node.left != null) {
                preOrder(node.left);
            }
            if (node.right != null) {
                preOrder(node.right);
            }
        }
    }

    // 중위 순회 InOrder : Left -> Root -> Right
    public static void inOrder(Node node) {
        if (node != null) {
            if (node.left != null) {
                inOrder(node.left);
            }
            sb.append(node.data);
            if (node.right != null) {
                inOrder(node.right);
            }
        }
    }

    // 후위순회 PostOrder : Left -> Right -> Root
    public static void postOrder(Node node) {
        if (node != null) {
            if (node.left != null) {
                postOrder(node.left);
            }
            if (node.right != null) {
                postOrder(node.right);
            }
            sb.append(node.data);
        }
    }

    public static void createNode(String data, String leftData, String rightData) {
        if (root == null) {
            root = new Node(data);
            if (!leftData.equals(".")) {
                root.left = new Node(leftData);
            }
            if (!rightData.equals(".")) {
                root.right = new Node(rightData);
            }
        } else {
            searchNode(root, data, leftData, rightData);
        }
    }

    public static void searchNode(Node node, String data, String leftData, String rightData) {
        if (node == null) {
            return;
        } else if (node.data.equals(data)) {
            if (!leftData.equals(".")) {
                node.left = new Node(leftData);
            }
            if (!rightData.equals(".")) {
                node.right = new Node(rightData);
            }
        } else {
            searchNode(node.left, data, leftData, rightData);
            searchNode(node.right, data, leftData, rightData);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            String c = st.nextToken();
            createNode(a, b, c);
        }

        preOrder(root);
        sb.append("\n");
        inOrder(root);
        sb.append("\n");
        postOrder(root);
        System.out.println(sb);
    }

    private static class Node {

        String data;
        Node left;
        Node right;

        public Node(String data) {
            this.data = data;
        }
    }
}
