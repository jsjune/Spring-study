package Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Room implements Comparable<Room>{
    int start;
    int end;

    public Room(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Room o) {
        return this.start - o.start;
    }
}

public class AllocationOfConferenceRooms {

    public int solution(int n, ArrayList<Room> arr) {
        int cnt = 1;
        int min = Integer.MAX_VALUE;
        Collections.sort(arr);
        for (Room r : arr) {
            min = Math.min(min, r.end);
            if (r.start >= min) {
                min = r.end;
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        AllocationOfConferenceRooms t = new AllocationOfConferenceRooms();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        ArrayList<Room> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Room(kb.nextInt(), kb.nextInt()));
        }
        System.out.println(t.solution(n, arr));
    }
}
