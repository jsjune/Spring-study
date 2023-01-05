package barkingDog.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 시리얼 번호 */
public class Bj1431 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                if ( sum(o1) == sum(o2)) {
                    return o1.compareTo(o2);
                } else {
                    return sum(o1) - sum(o2);
                }
            } else {
                return o1.length() - o2.length(); // 음수 오름차순, 양수 내림차순
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i] + "\n");
        }
        System.out.println(sb);
    }

    private static int sum(String o1) {
        int answer = 0;
        for (int i = 0; i < o1.length(); i++) {
            if (o1.charAt(i) <= '9' && o1.charAt(i) >= '0') {
                answer += o1.charAt(i) - '0';
            }
        }
        return answer;
    }

}
