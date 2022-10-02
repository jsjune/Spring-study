package grammar;

import java.util.stream.Stream;

public class StreamEx2 {
    public static void main(String[] args) {
        Stream<String> originalStream01 = Stream.of("samsung", "lg", "hyundai", "sk", "", "lotte");
        originalStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> originalStream02 = Stream.of("samsung", "lg", "hyundai", "sk", "", "lotte")
                .filter(i -> !i.isEmpty())
                .filter(i -> !i.startsWith("l"));
        originalStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        String[] strArray01 = {"Language", "Math", "Science", "Math", "Science"};
        Stream<String> strStreamOriginal = Stream.of(strArray01);
        strStreamOriginal.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> strStreamDistinct = Stream.of(strArray01).distinct();
        strStreamDistinct.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<Integer> intStreamOriginal = Stream.of(3, 9, 1, 2, 4);
        intStreamOriginal.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<Integer> intStreamSorted = Stream.of(3, 9, 1, 2, 4).sorted();
        intStreamSorted.forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("==============================================");

        Stream<String> stringStreamOriginal = Stream.of("마","가","나","라","다");
        stringStreamOriginal.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> stringStreamSorted = Stream.of("마","가","나","라","다").sorted();
        stringStreamSorted.forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
