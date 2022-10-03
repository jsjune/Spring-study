package grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx1 {
    public static void main(String[] args) {
        List<String> strList01 = new ArrayList<>();
        strList01.add("반도체");
        strList01.add("가전제품");
        strList01.add("휴대폰");
        strList01.add("자동차");
        strList01.add("배");
        strList01.stream().sorted().forEach(i -> System.out.print(i + " "));
        System.out.println();

        String[] strArray = new String[]{"Delta", "Alpha", "Bravo", "Charlie"};
        List<String> cvtStrList = Arrays.asList(strArray);
        cvtStrList.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> cvtStream01 = Arrays.stream(strArray);
        cvtStream01.sorted().forEach(i -> System.out.print(i + " "));
        System.out.println();

//        cvtStream01.forEach(i-> System.out.println()); // 스트림 이미 사용시 오류

        Stream<String> cvtStream02 = Arrays.stream(strArray, 2, 3);
        cvtStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> cvtStream03 = Stream.of(strArray);
        cvtStream03.forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("=================================================");
        Stream<String> streamCompany = Stream.of("Samsung", "LG", "Hyundai", "SK");
        streamCompany.forEach(i -> System.out.print(i + " "));
        System.out.println();

        IntStream intStream01 = IntStream.range(1, 7);
        intStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        IntStream intStream02 = IntStream.rangeClosed(1, 7);
        intStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        IntStream intRandom01 = new Random().ints();
        intRandom01.limit(5).forEach(i -> System.out.print(i + " "));
        System.out.println();

        IntStream intRandom02 = new Random().ints(3);
        intRandom02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("================================================");

        DoubleStream doublesRandom01 = new Random().doubles();
        doublesRandom01.limit(2).forEach(i -> System.out.print(i + " "));
        System.out.println();

        DoubleStream doublesRandom02 = new Random().doubles(5);
        doublesRandom02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        DoubleStream doubleStream01 = DoubleStream.of(0.1, 0.2, 0.3, 0.4, 0.5);
        doubleStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("================================================");

        Double[] arrayDouble = {0.31, 0.32, 0.33, 0.34};
        Stream<Double> doubleStream02 = Arrays.stream(arrayDouble);
        doubleStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> generateString01 = Stream.generate(() -> "Good company is best solution");
        generateString01.limit(3).forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("================================================");

        Stream<Double> generateMathRandom01 = Stream.generate(() -> Math.random());
        generateMathRandom01.limit(3).forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<Double> generateAnotherMathRandom = Stream.generate(Math::random);
        generateAnotherMathRandom.limit(3).forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("================================================");

        Stream<Integer> iterateStream01 = Stream.iterate(1, i -> i + 3);
        iterateStream01.limit(5).forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<Double> iterateStream02 = Stream.iterate(1.0, i -> i - 0.2);
        iterateStream02.limit(5).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
