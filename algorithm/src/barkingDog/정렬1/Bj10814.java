package barkingDog.정렬1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Member implements Comparable<Member> {

    public int age;
    public String name;

    public Member(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Member o) {
        return this.age - o.age;
    }
}

public class Bj10814 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Member> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            arr.add(new Member(age, name));
        }
        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (Member m : arr) {
            sb.append(m.age + " " + m.name + "\n");
        }
        System.out.println(sb);
    }
}
