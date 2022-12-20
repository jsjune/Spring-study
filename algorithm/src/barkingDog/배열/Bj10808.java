package barkingDog.배열;

import java.util.Scanner;

/* 알파벳 개수 */
public class Bj10808 {

    public int[] solution(String str) {
        char[] arr = alphapet();
        int[] result = new int[arr.length];
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (chars[i]==arr[j]) {
                    result[j]++;
                }
            }
        }
        return result;
    }

    private char[] alphapet() {
        char[] arr = new char[26];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (97 + i);
        }
        return arr;
    }

    public static void main(String[] args) {
        Bj10808 t = new Bj10808();
        Scanner kb = new Scanner(System.in);
        String str = kb.next();
        for (int result : t.solution(str)){
            System.out.print(result + " ");
        }
    }
}
