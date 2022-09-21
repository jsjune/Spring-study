package hashMapAndTreeSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FindAnagrams {
    public int solution(String a, String b) {
        int answer = 0, cnt = 0, lt = 0;
        HashMap<Character, Integer> aMap = new HashMap<>();
        HashMap<Character, Integer> bMap = new HashMap<>();
        for (char c : b.toCharArray()) {
            bMap.put(c, bMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < a.length(); i++) {
            cnt++;
            aMap.put(a.charAt(i), aMap.getOrDefault(a.charAt(i), 0) + 1);
            if (aMap.equals(bMap)) {
                answer++;
            }
            if (cnt >= b.length()) {
                aMap.put(a.charAt(lt), aMap.get(a.charAt(lt)) - 1);
                if (aMap.get(a.charAt(lt)) == 0) {
                    aMap.remove(a.charAt(lt));
                }
                lt++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        FindAnagrams t = new FindAnagrams();
        Scanner kb = new Scanner(System.in);
        String str1 = kb.next();
        String str2 = kb.next();
        System.out.println(t.solution(str1, str2));
    }
}
