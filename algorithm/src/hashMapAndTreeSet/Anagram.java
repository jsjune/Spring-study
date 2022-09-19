package hashMapAndTreeSet;

import java.util.HashMap;
import java.util.Scanner;

public class Anagram {
    public String solution(String str1, String str2) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        for (char c : str1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char c : str2.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        System.out.println(map1);
        System.out.println(map2);
        if (map1.equals(map2)) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static void main(String[] args) {
        Anagram t = new Anagram();
        Scanner kb = new Scanner(System.in);
        String str1 = kb.next();
        String str2 = kb.next();
        System.out.println(t.solution(str1,str2));
    }
}
