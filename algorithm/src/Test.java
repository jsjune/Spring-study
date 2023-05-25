public class Test {
    private int number;
    private int name;

    private Test() {
    }

    private static final Test SETTINGS = new Test();

    public static Test newInstance() {
        return SETTINGS;
    }
}
