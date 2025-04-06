package org.example.chatservice.entities;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Role {
   USER("ROLE_USER"), CONSULTANT("ROLE_CONSULTANT");
   private String code;

    Role(String code) {
        this.code = code;
    }

    public static Role fromCode(String code) {
        return Arrays.stream(Role.values())
            .filter(r -> r.code.equals(code))
            .findFirst()
            .orElseThrow();
    }
}
