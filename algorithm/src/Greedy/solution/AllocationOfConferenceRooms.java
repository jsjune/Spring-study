package Greedy.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Time implements Comparable<Time>{
    int start;
    int end;

    public Time(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Time o) {
        // 끝나는 시간이 같은면 시작시간 오름차순
        if (this.end == o.end) {
            return this.start - o.start;
        } else {
            return this.end - o.end;
        }
    }
}

public class AllocationOfConferenceRooms {

    public int solution(int n, ArrayList<Time> arr) {
        int cnt = 0;
        int et = 0;
        Collections.sort(arr);
        for (Time t : arr) {
            if (t.start >= et) {
                cnt++;
                et = t.end;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        AllocationOfConferenceRooms t = new AllocationOfConferenceRooms();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        ArrayList<Time> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Time(kb.nextInt(), kb.nextInt()));
        }
        System.out.println(t.solution(n, arr));
    }
}
