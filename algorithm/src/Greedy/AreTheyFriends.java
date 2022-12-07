package Greedy;

import com.sun.nio.sctp.SendFailedNotification;
import java.util.Scanner;

public class AreTheyFriends {
    static int[] unf;

    public static int Find(int v) {
        if (v == unf[v]) {
            System.out.println("v = " + v);
            return v;
        } else {
            System.out.println("v = " + v + " unf[v] = " + unf[v]);
            return unf[v] = Find(unf[v]);
        }
    }

    public static void Union(int a, int b) {
        int fa = Find(a);
        int fb = Find(b);
        System.out.println("================");
        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int m = kb.nextInt();
        unf = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;
        }
        for (int i = 1; i <= m; i++) {
            int a = kb.nextInt();
            int b = kb.nextInt();
            Union(a, b);
        }
        int a = kb.nextInt();
        int b = kb.nextInt();
        int fa = Find(a);
        int fb = Find(b);
        if (fa == fb) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        for (int i = 1; i <= n; i++) {
            System.out.println("unf[" + i + "] = " + unf[i]);
        }
    }

}
