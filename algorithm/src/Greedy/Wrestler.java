package Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Player implements Comparable<Player>{
    int height;
    int weight;

    public Player(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Player o) {
    //음수값을 기준으로 리턴되게 해라 (this - o,오름차순) (o - this,내림차순)
        return o.height - this.height;
    }
}

public class Wrestler {

    public int solution(int n, ArrayList<Player> arr) {
        int cnt = 0;
        Collections.sort(arr);
        int max = Integer.MIN_VALUE;
        for (Player p : arr) {
            if (p.weight > max) {
                max = p.weight;
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Wrestler t = new Wrestler();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        ArrayList<Player> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Player(kb.nextInt(), kb.nextInt()));
        }
        System.out.println(t.solution(n,arr));
    }
}
