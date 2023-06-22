package com.example.designpattern.creational_patterns.singleton;

public class Setting {
    private static final Setting INSTANCE;

    static {
        try {
            INSTANCE = new Setting();
        } catch (Exception e) {
            // 예외 처리 로직
            // 초기화에 실패한 경우 처리할 내용을 작성합니다.
            // 예를 들어, 로깅을 수행하거나 대체 동작을 정의할 수 있습니다.
            throw new ExceptionInInitializerError(e);
        }
    }

    private Setting()  {
        // checked 예외를 던지는 생성자
    }

    public static Setting getInstance() {
        return INSTANCE;
    }
}

