package barkingDog.연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Bj1158 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        LinkedList<Integer> list = new LinkedList<>();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        System.out.print("<");
        while (!list.isEmpty()) {
            for (int i = 1; i <= k; i++) {
                if (i == k) {
                    Integer a = list.remove();
                    if (list.size() == 0) {
                        System.out.print(a);
                    } else {
                        System.out.print(a + ", ");
                    }
                } else {
                    list.add(list.remove());
                }
            }
        }
        System.out.print(">");
    }

}
