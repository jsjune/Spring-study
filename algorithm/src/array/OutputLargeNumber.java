package array;

import java.util.ArrayList;
import java.util.Scanner;

public class OutputLargeNumber {
    public ArrayList<Integer> solution(int n, ArrayList<Integer> array) {
        ArrayList<Integer> answer = new ArrayList<>();
        array.add(0, 0);
        array.add(n + 1, 101);
        for (int i = 0; i < n; i++) {
            if (array.get(i) < array.get(i + 1)) {
                answer.add(array.get(i + 1));
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        OutputLargeNumber t = new OutputLargeNumber();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            array.add(kb.nextInt());
        }
        for (int x : t.solution(n, array)) {
            System.out.print(x + " ");
        }
    }
}
