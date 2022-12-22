package barkingDog.큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/* 큐 */
public class Bj10845 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<String> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] word = br.readLine().split(" ");
            if (word[0].equals("push")) {
                queue.add(word[1]);
            } else if (word[0].equals("pop")) {
                sb.append(queue.isEmpty() ? -1 : queue.poll()).append("\n");
            } else if (word[0].equals("size")) {
                sb.append(queue.size()).append("\n");
            } else if (word[0].equals("empty")) {
                sb.append(queue.isEmpty() ? 1 : 0).append("\n");
            } else if (word[0].equals("front")) {
                sb.append(queue.isEmpty() ? -1 : queue.peek()).append("\n");
            } else if (word[0].equals("back")) {
                sb.append(queue.isEmpty() ? -1 : back(queue)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static String back(Queue<String> queue) {
        String answer = "";
        for (String s : queue) {
            answer = s;
        }
        return answer;
    }
}
