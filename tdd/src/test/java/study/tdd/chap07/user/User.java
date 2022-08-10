package study.tdd.chap07.user;

import lombok.Getter;

@Getter
public class User {
    private String id;
    private String password;
    private String email;

    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
