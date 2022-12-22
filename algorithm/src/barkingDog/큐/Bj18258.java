package barkingDog.큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 큐 2 */
public class Bj18258 {
    public static String[] stack;
    public static int size = 0;
    public static int tail = 0;
    public static int head = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        stack = new String[n];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] word = br.readLine().split(" ");
            if (word[0].equals("push")) {
                push(word[1]);
            } else if (word[0].equals("pop")) {
                sb.append(pop()).append("\n");
            } else if (word[0].equals("size")) {
                sb.append(size).append("\n");
            } else if (word[0].equals("empty")) {
                sb.append(size==0 ? 1 : 0).append("\n");
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
            return stack[tail-1];
        }
    }

    private static String front() {
        if (size == 0) {
            return "-1";
        } else {
            return stack[head];
        }
    }

    private static String pop() {
        if (size == 0) {
            return "-1";
        } else {
            String answer = stack[head];
            size--;
            head++;
            return answer;
        }
    }

    private static void push(String s) {
        stack[tail] = s;
        size++;
        tail++;
    }
}
