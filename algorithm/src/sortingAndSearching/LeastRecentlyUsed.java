package sortingAndSearching;

import java.util.*;

public class LeastRecentlyUsed {
    public int[] solution(int s, int n, int[] arr) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (queue.contains(arr[i])) {
                queue.remove(arr[i]);
            }
            if (queue.size() == s) {
                queue.poll();
            }
            queue.add(arr[i]);
        }
        Collections.reverse((List<Integer>) queue);
        int[] answer = new int[s];
        for (int i = 0; i < s; i++) {
            answer[i] = queue.poll();
        }
        return answer;
    }
    public static void main(String[] args) {
        LeastRecentlyUsed t = new LeastRecentlyUsed();
        Scanner kb = new Scanner(System.in);
        int s = kb.nextInt();
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        for (int num : t.solution(s, n, arr)) {
            System.out.print(num + " ");
        }
    }
}
