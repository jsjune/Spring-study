package grammar;

import java.util.stream.Stream;

public class StreamEx4 {
    public static void main(String[] args) {
        Stream<String> strStream01 = Stream.of("samsung", "lg", "hyundai", "sk", "lotte").filter(i -> !i.startsWith("l"));
        strStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> workStream01 = Stream.of("휴가", "출근", "병가").filter(i -> !i.equals("출근"));
        workStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> countryStream01 = Stream.of("korea", "japan", "china", "america", "russia").filter(i -> i.length() < 6);
        countryStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> officeStream01 = Stream.of("Paper", "Pen", "", "Eraser").filter(i -> !i.isEmpty());
        officeStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> regionStream01 = Stream.of("Seoul", "Busan", "Seoul", "Incheon", "Gyungi", "Seoul").distinct();
        regionStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        System.out.println("=======================================================");

        Stream<String> workTypeStream01 = Stream.of("Account", "Procurement", "HumanResource", "Sales", "Manufacturing").limit(3);
        workTypeStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> workTypeStream02 = Stream.of("Account", "Procurement", "HumanResource", "Sales", "Manufacturing").limit(1);
        workTypeStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> skipStream01 = Stream.of("Account", "Procurement", "HumanResource", "Sales", "Manufacturing").skip(1);
        skipStream01.forEach(i -> System.out.print(i + " "));
        System.out.println();

        Stream<String> skipStream02 = Stream.of("Account", "Procurement", "HumanResource", "Sales", "Manufacturing").skip(3);
        skipStream02.forEach(i -> System.out.print(i + " "));
        System.out.println();

    }
}
