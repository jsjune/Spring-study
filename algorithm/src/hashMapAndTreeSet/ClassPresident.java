package hashMapAndTreeSet;

import java.util.HashMap;
import java.util.Scanner;

public class ClassPresident {
    public String solution(int n, String str) {
        String answer = "";
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char a : chars) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int num = Integer.MIN_VALUE;
        for (char a : chars) {
            num = Math.max(num, map.getOrDefault(a, 0));
        }
        for (char a : chars) {
            if (map.getOrDefault(a, 0)==num) {
                answer = String.valueOf(a);
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        ClassPresident t = new ClassPresident();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        String str = kb.next();
        System.out.println(t.solution(n,str));
    }
}
