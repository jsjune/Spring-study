//package barkingDog.배열;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.StringTokenizer;
///* 방 배정 */
//class Student implements Comparable<Student> {
//
//    int sex;
//    int grade;
//
//    public Student(int sex, int grade) {
//        this.sex = sex;
//        this.grade = grade;
//    }
//
//    @Override
//    public int compareTo(Student o) {
//        if (this.grade == o.grade) {
//            return this.sex - o.sex;
//        } else {
//            return this.grade - o.grade;
//        }
//    }
//}
//
//public class Bj13300 {
//
//    static int n, k;
//
//    public int solution(ArrayList<Student> arr) {
//        Collections.sort(arr);
//        int grade = Integer.MIN_VALUE;
//        int result = 0;
//        int mcnt = 0;
//        int gcnt = 0;
//        for (int i = 0; i < n; i++) {
//            if (grade != arr.get(i).grade) {
//                mcnt = 0;
//                gcnt = 0;
//            }
//            grade = Math.max(grade, arr.get(i).grade);
//            if (grade == arr.get(i).grade && arr.get(i).sex == 0) {
//                mcnt++;
//                result++;
//                if (mcnt == 1) {
//                    result++;
//                }
//                if (mcnt % k != 0) {
//                    result--;
//                }
//            }
//            if (grade == arr.get(i).grade && arr.get(i).sex == 1) {
//                gcnt++;
//                result++;
//                if (gcnt == 1) {
//                    result++;
//                }
//                if (gcnt % k != 0) {
//                    result--;
//                }
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) throws IOException {
//        Bj13300 t = new Bj13300();
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st1 = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st1.nextToken());
//        k = Integer.parseInt(st1.nextToken());
//        ArrayList<Student> arr = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            arr.add(
//                new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
//        }
//        System.out.println(t.solution(arr));
//    }
//
//}
