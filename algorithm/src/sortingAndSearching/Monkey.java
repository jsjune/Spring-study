package sortingAndSearching;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Monkey {
    public void solution(int n, int[] arr) {
        int cheolsu = Integer.MIN_VALUE;
        int partner = Integer.MAX_VALUE;
        for (int i = 0; i < n-1; i++) {
            if (arr[i] > arr[i + 1]) {
                cheolsu = Math.max(cheolsu, arr[i]);
                partner = Math.min(partner,arr[i + 1]);
            }
        }
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.print(list.indexOf(cheolsu)+1+" ");
        System.out.print(list.lastIndexOf(partner)+1);
    }
    public static void main(String[] args) {
        Monkey t = new Monkey();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        t.solution(n, arr);
    }
}
