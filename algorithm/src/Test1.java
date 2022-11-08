import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5};
        int lt = Arrays.stream(arr).max().getAsInt();
        System.out.println(lt);
    }
}
