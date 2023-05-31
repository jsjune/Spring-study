public class Practice {
    public static void main(String[] args) {
        Test test = Test.builder()
                .name("가나다")
                .content("contents")
                .check(true)
                .build();
        System.out.println(test.toString());
    }
}
