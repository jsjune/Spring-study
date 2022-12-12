package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Bricks implements Comparable<Bricks> {
    public int area;
    public int height;
    public int weight;

    public Bricks(int area, int height, int weight) {
        this.area = area;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Bricks o) {
        return o.area - this.area;
    }
}

public class BuildingTheHighestTower {
    static int[] dy;

    public int solution(ArrayList<Bricks> arr) {
        int answer = 0;
        Collections.sort(arr);
        dy[0] = arr.get(0).height;
        answer = dy[0];
        for (int i = 1; i < arr.size(); i++) {
            System.out.println("======================");
            System.out.println("i = " + i);
            int max_h = 0;
            for (int j = i - 1; j >= 0; j--) {
                System.out.println("j = " + j);
                if (arr.get(j).weight > arr.get(i).weight && dy[j] > max_h) {
                    max_h = dy[j];
                    System.out.println(max_h);
                }
            }
            dy[i] = max_h + arr.get(i).height;
            answer = Math.max(answer, dy[i]);
            System.out.println(Arrays.toString(dy));
        }
        return answer;
    }

    public static void main(String[] args) {
        BuildingTheHighestTower t = new BuildingTheHighestTower();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        dy = new int[n];
        ArrayList<Bricks> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Bricks(kb.nextInt(), kb.nextInt(), kb.nextInt()));
        }
        System.out.println(t.solution(arr));
    }

}
