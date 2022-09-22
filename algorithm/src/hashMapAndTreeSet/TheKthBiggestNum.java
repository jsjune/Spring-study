package hashMapAndTreeSet;

import java.util.*;

public class TheKthBiggestNum {
    public int solution(int a, int b, ArrayList<Integer> list) {
        Collections.sort(list);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < a - 2; i++) {
            for (int j = i + 1; j < a - 1; j++) {
                for (int k = j + 1; k < a; k++) {
                    set.add(list.get(i) + list.get(j) + list.get(k));
                }
            }
        }
        Integer[] ans = set.toArray(new Integer[0]);
        Arrays.sort(ans);
        int answer;
        if (ans.length > b) {
            answer = ans[ans.length - b];
        } else {
            answer=-1;
        }
        return answer;
    }

    public static void main(String[] args) {
        TheKthBiggestNum t = new TheKthBiggestNum();
        Scanner kb = new Scanner(System.in);
        int a = kb.nextInt();
        int b = kb.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            list.add(kb.nextInt());
        }
        System.out.println(t.solution(a, b, list));
    }
}
