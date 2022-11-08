import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= 100000; i++) {
            for (int j = 2; j <= i; j++) {
                if (i % j == 0) {
                    count++;
                }
            }
            if (count == 1) {
                list.add(i);
            }
            count = 0;
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (arr.length >= list.get(i)) {
                answer += arr[list.get(i) - 1];
            }
        }
        System.out.println(answer);
    }
}
