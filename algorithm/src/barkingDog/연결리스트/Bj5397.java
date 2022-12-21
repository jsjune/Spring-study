package barkingDog.연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Bj5397 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            LinkedList<Character> list = new LinkedList<>();
            ListIterator<Character> iter = list.listIterator();
            char[] chars = br.readLine().toCharArray();
            for (char c : chars) {
                if (c == '>') {
                    if (iter.hasNext()) {
                        iter.next();
                    }
                } else if (c == '<') {
                    if (iter.hasPrevious()) {
                        iter.previous();
                    }
                } else if (c == '-') {
                    if (iter.hasPrevious()) {
                        iter.previous();
                        iter.remove();
                    }
                } else {
                    iter.add(c);
                }
            }
            for (char c : list) {
                result.append(c);
            }
            result.append("\n");
        }
        System.out.println(result.toString());
    }

}
