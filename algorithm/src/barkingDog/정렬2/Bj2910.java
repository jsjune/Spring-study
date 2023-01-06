package barkingDog.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

/* 빈도 정렬 */
class Cipher implements Comparable<Cipher>{
    int num;
    int cnt;
    int index;

    public Cipher(int num, int cnt, int index) {
        this.num = num;
        this.cnt = cnt;
        this.index = index;
    }

    @Override
    public int compareTo(Cipher o) {
        if (this.cnt == o.cnt) {
            return this.index - o.index;
        } else {
            return o.cnt - this.cnt;
        }
    }
}
public class Bj2910 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        HashMap<Integer, Cipher> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.putIfAbsent(num, new Cipher(num, 0, i));
            Cipher cipher = map.get(num);
            cipher.cnt++;
            map.put(num, cipher);
        }
        ArrayList<Cipher> arr = new ArrayList<>(map.values());
        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (Cipher cipher : arr) {
            for (int i = 0; i < cipher.cnt; i++) {
                sb.append(cipher.num + " ");
            }
        }
        System.out.println(sb);

    }
}
