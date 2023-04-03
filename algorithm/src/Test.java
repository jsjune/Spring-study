import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        String[] a = {"a","b","c"};
        String[] b = a;
        String[] c = a.clone();
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(c));

    }
}
