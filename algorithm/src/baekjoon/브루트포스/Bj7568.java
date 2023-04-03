package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj7568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            people.add(new Person(x, y));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int ranking = 1;
            int weight = people.get(i).weight;
            int height = people.get(i).height;
            for (int j = 0; j < n; j++) {
                if (weight < people.get(j).weight && height < people.get(j).height) {
                    ranking++;
                }
            }
            sb.append(ranking).append(" ");
        }
        System.out.println(sb);

    }

    private static class Person {
        int weight;
        int height;

        public Person(int weight, int height) {
            this.weight = weight;
            this.height = height;
        }
    }
}
