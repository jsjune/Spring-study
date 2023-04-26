import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                continue;
            }
            System.out.println(i);
        }
    }
}
