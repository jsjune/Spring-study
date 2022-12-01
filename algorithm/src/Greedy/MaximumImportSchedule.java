package Greedy;

/*
 * PriorityQueue
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

class Business implements Comparable<Business> {

    int pay;
    int day;

    public Business(int pay, int day) {
        this.pay = pay;
        this.day = day;
    }

    @Override
    public int compareTo(Business o) {
        return o.day - this.day;
    }
}

public class MaximumImportSchedule {

    static int n, max = Integer.MIN_VALUE;

    public int solution(ArrayList<Business> arr) {
        int answer = 0;
        Collections.sort(arr);
        // 큰값을 우선순위로 뺴준다.
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        int j = 0;
        for (int i = max; i >= 1; i--) {
            for (; j < n; j++) {
                if (arr.get(j).day < i) {
                    break;
                }
                pQ.offer(arr.get(j).pay);
            }
            if (!pQ.isEmpty()) {
                answer += pQ.poll();
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        MaximumImportSchedule t = new MaximumImportSchedule();
        Scanner kb = new Scanner(System.in);
        n = kb.nextInt();
        ArrayList<Business> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int pay = kb.nextInt();
            int day = kb.nextInt();
            arr.add(new Business(pay, day));
            if (day > max) {
                max = day;
            }
        }
        System.out.println(t.solution(arr));
    }
}
