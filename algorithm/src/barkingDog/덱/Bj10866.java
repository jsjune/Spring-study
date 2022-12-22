package barkingDog.덱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/* 덱 */
public class Bj10866 {

    public static String[] dq;
    public static int size = 0;
    public static int head;
    public static int tail;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        Deque<String> dq = new LinkedList<>();
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            String[] word = br.readLine().split(" ");
//            if (word[0].equals("push_front")) {
//                dq.addFirst(word[1]);
//            } else if (word[0].equals("push_back")) {
//                dq.addLast(word[1]);
//            } else if (word[0].equals("pop_front")) {
//                sb.append(dq.isEmpty() ? -1 : dq.pollFirst()).append("\n");
//            } else if (word[0].equals("pop_back")) {
//                sb.append(dq.isEmpty() ? -1 : dq.pollLast()).append("\n");
//            } else if (word[0].equals("size")) {
//                sb.append(dq.size()).append("\n");
//            } else if (word[0].equals("empty")) {
//                sb.append(dq.isEmpty() ? 1 : 0).append("\n");
//            } else if (word[0].equals("front")) {
//                sb.append(dq.isEmpty() ? -1 : dq.peekFirst()).append("\n");
//            } else if (word[0].equals("back")) {
//                sb.append(dq.isEmpty() ? -1 : dq.peekLast()).append("\n");
//            }
//        }
//        System.out.println(sb);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dq = new String[n * 2 + 1];
        head = n;
        tail = n;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String[] word = br.readLine().split(" ");
            if (word[0].equals("push_front")) {
                push_front(word[1]);
            } else if (word[0].equals("push_back")) {
                push_back(word[1]);
            } else if (word[0].equals("pop_front")) {
                sb.append(pop_front()).append("\n");
            } else if (word[0].equals("pop_back")) {
                sb.append(pop_back()).append("\n");
            } else if (word[0].equals("size")) {
                sb.append(size).append("\n");
            } else if (word[0].equals("empty")) {
                sb.append(size == 0 ? 1 : 0).append("\n");
            } else if (word[0].equals("front")) {
                sb.append(front()).append("\n");
            } else if (word[0].equals("back")) {
                sb.append(back()).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static String back() {
        if (size == 0) {
            return "-1";
        } else {
            return dq[tail-1];
        }
    }

    private static String front() {
        if (size == 0) {
            return "-1";
        } else {
            return dq[head];
        }
    }

    private static String pop_back() {
        if (size == 0) {
            return "-1";
        } else {
            String answer = dq[--tail];
            size--;
            return answer;
        }
    }

    private static String pop_front() {
        if (size == 0) {
            return "-1";
        } else {
            String answer = dq[head++];
            size--;
            return answer;
        }
    }

    private static void push_back(String s) {
        dq[tail++] = s;
        size++;
    }

    private static void push_front(String s) {
        dq[--head] = s;
        size++;
    }
}
