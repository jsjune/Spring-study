package barkingDog.이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Test {

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(10);
        queue.add(1);
        queue.add(4);
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(4, tm.getOrDefault(4, 0) + 1);
        tm.put(1, tm.getOrDefault(1, 0) + 1);
        tm.put(10, tm.getOrDefault(10, 0) + 1);
        tm.put(2, tm.getOrDefault(2, 0) + 1);
        tm.put(2, tm.getOrDefault(2, 0) + 1);
        tm.put(6, tm.getOrDefault(6, 0) + 1);
        tm.put(6, tm.getOrDefault(6, 0) + 1);
        tm.put(6, tm.getOrDefault(6, 0) + 1);
        System.out.println(tm.put(6, tm.get(6)-1));
        tm.put(7, tm.getOrDefault(7, 0) + 1);
        tm.put(5, tm.getOrDefault(5, 0) + 1);

        for (Integer integer : tm.keySet()) {
            System.out.println("================================");
            System.out.println(integer);
            System.out.println(tm.get(integer));
        }
//
//        public static void main(String[] args) throws IOException {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            int T = Integer.parseInt(br.readLine());
//
//            StringBuilder sb = new StringBuilder();
//            while (T-- != 0) {
//                TreeMap<Integer, Integer> tm = new TreeMap<>();
//                int n = Integer.parseInt(br.readLine());
//                while (n-- != 0) {
//                    String[] str = br.readLine().split(" ");
//                    String alp = str[0];
//                    int num = Integer.parseInt(str[1]);
//                    if(alp.equals("I")){
//                        tm.put(num, tm.getOrDefault(num, 0) + 1);
//                    }else{
//                        if(!tm.isEmpty()) {
//                            Integer number = num == 1 ? tm.lastKey() : tm.firstKey();
//                            if (tm.get(number) == 1) {
//                                tm.remove(number);
//                            }
//                        }
//                    }
//                }
//                sb.append(tm.isEmpty() ? "EMPTY\n" : tm.lastKey() + " " + tm.firstKey() + "\n");
//            }
//            System.out.println(sb);
    }
}
