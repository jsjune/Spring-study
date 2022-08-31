package string;

import java.util.Scanner;
//답
public class Code {
    public String solution(int num, String str) {
        String answer = "";
        for (int i = 0; i < num; i++) {
            String tmp = str.substring(0, 7).replace('#', '1').replace('*', '0');
            int number = Integer.parseInt(tmp, 2);
            answer += (char)number;
            str = str.substring(7);
        }
        return answer;
    }

    public static void main(String[] args) {
        Code t = new Code();
        Scanner kb = new Scanner(System.in);
        int num = kb.nextInt();
        String str = kb.next();
        System.out.println(t.solution(num,str));
    }
}
