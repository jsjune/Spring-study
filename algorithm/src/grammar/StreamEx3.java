package grammar;

import java.util.Comparator;
import java.util.stream.Stream;

public class StreamEx3 {
    public static void main(String[] args) {
        Stream<String> strStream01 = Stream.of("b", "c", "a");
        strStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> strStream02 = Stream.of("b", "c", "a").sorted();
        strStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> strStream03 = Stream.of("b", "c", "a").sorted((i1, i2) -> i1.compareTo(i2));
        strStream03.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> strStream04 = Stream.of("b", "c", "a").sorted(String::compareTo);
        strStream04.forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("==========================================");

        Stream<String> reverseStream01 = Stream.of("b", "c", "a").sorted(Comparator.reverseOrder());
        reverseStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> caseStream01 = Stream.of("b", "c", "A", "A", "B");
        caseStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> caseStream02 = Stream.of("b", "c", "A", "A", "B").sorted();
        caseStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("============================================");

        Stream<String> caseStream03 = Stream.of("b", "c", "A", "A", "B").sorted(String.CASE_INSENSITIVE_ORDER);
        caseStream03.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> caseStream04 = Stream.of("b", "c", "A", "A", "B").sorted(String.CASE_INSENSITIVE_ORDER.reversed());
        caseStream04.forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("============================================");

        Stream<String> caseStream05 = Stream.of("bb", "Aaaa", "a", "Bbbbb", "ccc").sorted(Comparator.comparing(String::length));
        caseStream05.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> caseStream06 = Stream.of("bb", "Aaaa", "a", "Bbbbb", "ccc").sorted(Comparator.comparing(i -> i.length()));
        caseStream06.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> caseStream07 = Stream.of("bb", "Aaaa", "a", "Bbbbb", "ccc").sorted(Comparator.comparing(String::length).reversed());
        caseStream07.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
