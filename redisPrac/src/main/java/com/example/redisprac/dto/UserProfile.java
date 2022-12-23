package com.example.redisprac.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class UserProfile {
    @JsonProperty
    private String name;
    @JsonProperty
    private int age;

    public UserProfile(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
