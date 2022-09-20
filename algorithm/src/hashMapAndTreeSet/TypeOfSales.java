package hashMapAndTreeSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TypeOfSales {
    public ArrayList<Integer> solution(int a, int b, int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < a; i++) {
            cnt++;
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            if (cnt >= b) {
                answer.add(map.size());
                map.put(arr[i - b + 1], map.get(arr[i - b + 1]) - 1);
                if (map.get(arr[i - b + 1]) == 0) {
                    map.remove(arr[i - b + 1]);
                }
            }
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
