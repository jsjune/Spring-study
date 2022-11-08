package StackAndQueue;

import java.util.*;

class Person{
    int id;
    int priority;
    public Person(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }
}

public class EmergencyRoom {
    public int solution(int n, int m, int[] arr) {
        int answer = 1;
        Queue<Person> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            q.add(new Person(i, arr[i]));
        }

        while (!q.isEmpty()) {
            Person tmp = q.poll();
            for (Person x : q) {
                if (x.priority > tmp.priority) {
                    q.add(tmp);
                    tmp = null;
                    break;
                }
            }
            if (tmp != null) {
                if (tmp.id == m) return answer;
                else answer++;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        EmergencyRoom t = new EmergencyRoom();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int m = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(n, m, arr));
    }
}
