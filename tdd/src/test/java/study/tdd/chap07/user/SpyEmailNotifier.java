package study.tdd.chap07.user;

import lombok.Getter;

@Getter
public class SpyEmailNotifier implements EmailNotifier{
    private boolean called;
    private String email;

    public boolean isCalled() {
        return called;
    }

    @Override
    public void sendRegisterEmail(String email) {
        this.called = true;
        this.email = email;
    }
}
