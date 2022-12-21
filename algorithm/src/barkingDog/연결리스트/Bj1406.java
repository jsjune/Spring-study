package barkingDog.연결리스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class Bj1406 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars = br.readLine().toCharArray();
        LinkedList<Character> list = new LinkedList<>();
        for (char c : chars) {
            list.add(c);
        }

        int n = Integer.parseInt(br.readLine());

        ListIterator<Character> iter = list.listIterator();
        while (iter.hasNext()) {
            iter.next();
        }

        while (n-- > 0) {
            String[] word = br.readLine().split(" ");
            if (word[0].equals("L")) {
                if (iter.hasPrevious()) {
                    iter.previous();
                }
            } else if (word[0].equals("D")) {
                if (iter.hasNext()) {
                    iter.next();
                }
            } else if (word[0].equals("B")) {
                if (iter.hasPrevious()) {
                    iter.previous();
                    iter.remove();
                }
            } else if (word[0].equals("P")) {
                iter.add(word[1].charAt(0));
            }
        }
        for (char c : list) {
            bw.append(c);
        }
        bw.flush();
        bw.close();
    }


}