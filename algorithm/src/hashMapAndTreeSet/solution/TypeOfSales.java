package hashMapAndTreeSet.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TypeOfSales {
    public ArrayList<Integer> solution(int a, int b, int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < b - 1; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int lt=0;
        for (int rt = b - 1; rt < a; rt++) {
            map.put(arr[rt], map.getOrDefault(arr[rt], 0) + 1);
            answer.add(map.size());
            map.put(arr[lt], map.get(arr[lt]) - 1);
            if(map.get(arr[lt])==0) map.remove(arr[lt]);
            lt++;
        }
        return answer;
    }

    public static void main(String[] args) {
        TypeOfSales t = new TypeOfSales();
        Scanner kb = new Scanner(System.in);
        int a = kb.nextInt();
        int b = kb.nextInt();
        int[] arr = new int[a];
        for (int i = 0; i < a; i++) {
            arr[i] = kb.nextInt();
        }
        for (int num : t.solution(a, b, arr)) {
            System.out.print(num + " ");
        }
    }
}
