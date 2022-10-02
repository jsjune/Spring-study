package grammar;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamEx5 {
    public static void main(String[] args) {
        // map은 Stream에 있는 값들을 계산해줘서 Stream으로 return 해준다.

        Stream<Integer> mapStream01 = Stream.of(3, 4, 5).map(i -> i + 1);
        mapStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<Boolean> mapStream02 = Stream.of(3, 4, 5).map(i -> i < 5);
        mapStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<Boolean> mapStream03 = Stream.of("samsung", "lg", "hyundai", "sk").map(s -> s.equals("sk"));
        mapStream03.forEach(i -> System.out.print(i + " "));
        System.out.println();

        String[][] multipleStringArray = {{"a", "b", "c"}, {"가", "나", "다"}, {"good", "nice", "well"}};
        Stream<String> flatMapStream01 = Stream.of(multipleStringArray).flatMap(arr -> Arrays.stream(arr));
        flatMapStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<Integer> concatStream01 = Stream.of(1, 2, 3);
        Stream<Integer> concatStream02 = Stream.of(4, 5, 6);
        Stream<Integer> concatResultStream = Stream.concat(concatStream01, concatStream02);
        concatResultStream.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> multipleMethodStream01 = Stream.of("Samsung", "LG", "", "SK", "Hyundai", "Hyundai", "Hyundai")
                .distinct()
                .filter(s -> !s.isEmpty())
                .sorted(String.CASE_INSENSITIVE_ORDER);
        multipleMethodStream01.forEach(i-> System.out.print(i + " "));
        System.out.println();
    }

}
