package com.example.designpattern.behavioral_patterns.state._02_after;

public interface State {
    void addReview(String review, Student student);

    void addStudent(Student student);
}
