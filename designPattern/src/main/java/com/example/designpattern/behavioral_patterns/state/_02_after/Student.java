package com.example.designpattern.behavioral_patterns.state._02_after;

import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    private Set<OnlineCourse> onlineCourses = new HashSet<>();

    public boolean isAvailable(OnlineCourse onlineCourse) {
        return onlineCourses.contains(onlineCourse);
    }

    public void addPrivate(OnlineCourse onlineCourse) {
        this.onlineCourses.add(onlineCourse);
    }
}
