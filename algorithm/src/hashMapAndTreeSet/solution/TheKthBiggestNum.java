package hashMapAndTreeSet.solution;

import java.util.*;

public class TheKthBiggestNum {
    public int solution(int a, int b, int[] arr) {
        int answer = -1;
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < a; i++) {
            for (int j = i+1; j < a; j++) {
                for (int k = j+1; k < a; k++) {
                    set.add(arr[i] + arr[j] + arr[k]);
                }
            }
        }
        int cnt = 0;
        for (int x : set) {
            cnt++;
            if(cnt==b)return x;
        }
        return answer;
    }

    public static void main(String[] args) {
        TheKthBiggestNum t = new TheKthBiggestNum();
        Scanner kb = new Scanner(System.in);
        int a = kb.nextInt();
        int b = kb.nextInt();
        int[] arr = new int[a];
        for (int i = 0; i < a; i++) {
            arr[i] = kb.nextInt();
        }
        System.out.println(t.solution(a, b, arr));
    }
}
