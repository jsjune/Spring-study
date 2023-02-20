import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        s.append(1);
        s.append(4);
        s.append(8);
        s.append(3);
        s.append(6);
        Arrays.stream(s.toString().split("")).sorted().forEach(System.out::println);
    }
}
