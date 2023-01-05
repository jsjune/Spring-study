//package barkingDog.정렬1;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class BJ11728_2 {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int a = Integer.parseInt(st.nextToken());
//        int b = Integer.parseInt(st.nextToken());
//        int[] arrA = new int[a];
//        int[] arrB = new int[b];
//        mergeSort(0, a + b);
//    }
//
//    private static void mergeSort(int st, int en) {
//        int mid = (st + en) / 2;
//        mergeSort(st,mid);
//        mergeSort(mid, en);
//        merge(st, en);
//    }
//
//    private static void merge(int st, int en) {
//        int mid = (st + en) / 2;
//        int lx = st;
//        int rx = mid;
//        for (int i = st; i < en; i++) {
//            if(rx==en)
//
//        }
//    }
//}
