package array;

import java.util.*;

public class LookingForTheRank {
    public int[] solution(int n, int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] tmp = arr.clone();

        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (arr[i] < arr[j]) {
                    num = arr[i];
                    arr[i] = arr[j];
                    arr[j] = num;
                }
            }
            list.add(arr[i]);
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = list.indexOf(tmp[i]) + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        LookingForTheRank t = new LookingForTheRank();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = kb.nextInt();
        }
        for (int answer : t.solution(n, arr)) {
            System.out.print(answer + " ");
        }
    }
}
