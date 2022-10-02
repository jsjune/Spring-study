package grammar;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx6 {
    public static void main(String[] args) {
        long getCountNumber = Stream.of(1, 2, 3, 4, 5).count();
        System.out.println("스트림의 총 개수 : " + getCountNumber);

        long getCountNumber01 = Stream.of("Ship", "Energy", "Plant", "Special", "Robot", "Engine").count();
        System.out.println("스트림의 총 개수 : " + getCountNumber01);

        Optional<Integer> maxStream01 = Stream.of(3, 5, 1).max(Integer::compare);
        Integer integer = maxStream01.get();
        System.out.println("최대값 : " + integer);

        Integer integer1 = Stream.of(3, 7, 1).max(Integer::compare).get();
        System.out.println("최대값 : " + integer1);

        Optional<Integer> min = Stream.of(3, 5, 1).min(Integer::compare);
        Integer integer11 = min.get();
        System.out.println("최소값 : " + integer11);

        Optional<String> first = Stream.of("", "account", "", "humanResource", "procurement", "sales", "manufacturing")
                .filter(s -> !s.isEmpty())
                .findFirst();
        System.out.println("일치하는 첫 번째 값 : " + first.get());

        Optional<String> first1 = Stream.of("반도체", "가전", "전장").findFirst();
        System.out.println("일치하는 첫 번째 값 : " + first1.get());

        Optional<String> any = Stream.of("", "account", "", "humanResource", "procurement", "sales", "manufacturing")
                .filter(s -> !s.isEmpty())
                .findAny();
        System.out.println(any.get());

        Optional<String> any1 = Stream.of("반도체", "가전", "전장").findAny();
        System.out.println(any1.get());

        System.out.println("==========================================");

        boolean b = Stream.of("samsung", "lg", "hyundai", "sk").anyMatch(s -> s.startsWith("s"));
        System.out.println(b);

        boolean b1 = Stream.of("samsung", "lg", "hyundai", "sk").anyMatch(s -> s.startsWith("D"));
        System.out.println(b1);

        boolean b2 = Stream.of("samsung", "lg", "hyundai", "sk").allMatch(s -> s.equals("samsung"));
        System.out.println(b2);

        boolean b3 = Stream.of("samsung", "samsung", "samsung").allMatch(s -> s.equals("samsung"));
        System.out.println(b3);

        boolean b4 = Stream.of("samsung", "samsung", "samsung", "sk").allMatch(s -> s.startsWith("s"));
        System.out.println(b4);

        System.out.println("============================================");

        boolean b5 = Stream.of("samsung", "lg", "hyundai", "sk").noneMatch(s -> s.equals("samsung"));
        System.out.println(b5);

        boolean b6 = Stream.of("samsung", "lg", "hyundai", "sk").noneMatch(s -> s.equals("ls"));
        System.out.println(b6);

        System.out.println("============================================");

        String[] strings = Stream.of("korea", "america", "japan", "china", "russia").toArray(String[]::new);
        System.out.println("스트림 -> 배열 : " + Arrays.toString(strings));

        Integer reduce = Stream.of(1, 2, 3).reduce(0, Integer::sum);
        System.out.println("reduce = " + reduce);

        Integer reduce1 = Stream.of(1, 2, 3).reduce(100, Integer::sum);
        System.out.println("reduce1 = " + reduce1);

        Integer reduce2 = Stream.of(1, 2, 3).reduce(0, (x, y) -> x + y);
        System.out.println("reduce2 = " + reduce2);

        Integer reduce3 = Stream.of(3, 3, 3).reduce(100, (x, y) -> x * y);
        System.out.println("reduce3 = " + reduce3);

        Integer reduce4 = Stream.of(3, 3, 3).reduce(0, (x, y) -> x * y);
        System.out.println("reduce4 = " + reduce4);

        Optional<Integer> reduce5 = Stream.of(1, 2, 3).reduce(Integer::sum);
        System.out.println("reduce5.get() = " + reduce5.get());

        Optional<Integer> reduce6 = Stream.of(1, 2, 3).reduce(Integer::max);
        System.out.println("reduce6.get() = " + reduce6.get());

        Optional<Integer> reduce7 = Stream.of(1, 2, 3).reduce(Integer::min);
        System.out.println("reduce7.get() = " + reduce7.get());

        System.out.println("===================================================");

        String[] strArray = {"한국", "한국", "대한민국", "미국", "미합중국", "중국", "중화민국"};
        Set<String> collect = Stream.of(strArray).collect(Collectors.toSet());
        System.out.println("collect = " + collect);

        List<String> collect1 = Stream.of(strArray).collect(Collectors.toList());
        System.out.println("collect1 = " + collect1);
    }
}
