package Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/* 최소스패닝트리 : 크루스칼 */

class Edge2 implements Comparable<Edge2> {
    public int v1;
    public int v2;
    public int cost;

    public Edge2(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge2 o) {
        return this.cost - o.cost; // 오름차순
    }
}

public class Wonderland {
    static int[] unf;

    public static int Find(int v) {
        if (unf[v] == v) {
            return v;
        } else {
            return unf[v] = Find(unf[v]);
        }
    }

    public static void Union(int a, int b) {
        int fa = Find(a);
        int fb = Find(b);
        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int v = kb.nextInt();
        int e = kb.nextInt();
        unf = new int[v + 1];
        ArrayList<Edge2> arr = new ArrayList<>();
        for (int i = 1; i <= v; i++) {
            unf[i] = i;
        }
        for (int i = 1; i <= e; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            int c = kb.nextInt();
            arr.add(new Edge2(a, b, c));
        }
        int answer = 0;
        Collections.sort(arr);
        for (Edge2 ob : arr) {
            int fv1 = Find(ob.v1);
            int fv2 = Find(ob.v2);
            if (fv1 != fv2) {
                answer += ob.cost;
                Union(ob.v1, ob.v2);
            }
        }
        System.out.println(answer);
    }
}
