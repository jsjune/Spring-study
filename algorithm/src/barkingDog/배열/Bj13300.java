package barkingDog.배열;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj13300 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] count = new int[7][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int sex = Integer.parseInt(st.nextToken()); // 성별
            int grade = Integer.parseInt(st.nextToken()); // 학년

            count[grade][sex]++;
        }
        System.out.println(Arrays.deepToString(count));

        int room = 0;

        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                if (count[i][j] >= K) {
                    room += (count[i][j] / K);
                    if (count[i][j] % K >= 1) {
                        room++;
                    }
                    continue;
                }
                if (count[i][j] == 0) {
                    continue;
                } else {
                    room++;
                }
            }
        }

        System.out.println(room);

    }
}