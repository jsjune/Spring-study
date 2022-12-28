package barkingDog.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 숨바꼭질 */
public class Bj1697 {

    static int[] arr = new int[100_001];
    static int n, k, cnt = 0;

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer num = q.poll();
                if (num == k) {
                    return;
                }
                if (num - 1 >= 0 && num - 1 <= 100000 && arr[num - 1] == 0) {
                    arr[num - 1] = arr[num] + 1;
                    q.offer(num - 1);
                }
                if (num + 1 >= 0 && num + 1 <= 100000 && arr[num + 1] == 0) {
                    arr[num + 1] = arr[num] + 1;
                    q.offer(num + 1);
                }
                if (num * 2 >= 0 && num * 2 <= 100000 && arr[num * 2] == 0) {
                    arr[num * 2] = arr[num] + 1;
                    q.offer(num * 2);
                }
            }
            cnt++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr[n] = 1;
        bfs();
        System.out.println(cnt);
    }

}
