package barkingDog.배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 방 번호 */
public class Bj1475 {

    public int solution(String n) {
        int[] arr = new int[10];
        for (int i = 0; i < n.length(); i++) {
            int num = Character.getNumericValue(n.charAt(i));
            if (num == 6 || num == 9) {
                if (arr[6] > arr[9]) {
                    arr[9]++;
                } else {
                    arr[6]++;
                }
            } else {
                arr[num]++;
            }
        }
        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            answer = Math.max(answer, arr[i]);
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        Bj1475 t = new Bj1475();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        System.out.println(t.solution(n));
    }

}
