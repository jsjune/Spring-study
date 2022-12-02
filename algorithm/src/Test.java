import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        int n = 5;
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < 4; i++) {
            arr.get(i).add(i);
        }
        System.out.println(arr);
    }
}
