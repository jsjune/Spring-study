public class Test {
    private String name;
    private String content;
    private boolean check;

    public Test(String name, String content, boolean check) {
        this.name = name;
        this.content = content;
        this.check = check;
    }

    public Test(Builder builder) {
        this.name = builder.name;
        this.content = builder.content;
        this.check = builder.check;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String content;
        private boolean check;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder check(boolean check) {
            this.check = check;
            return this;
        }

        public Test build() {
            return new Test(this);
        }
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", check=" + check +
                '}';
    }
}
