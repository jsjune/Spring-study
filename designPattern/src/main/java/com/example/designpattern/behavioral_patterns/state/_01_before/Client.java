package com.example.designpattern.behavioral_patterns.state._01_before;

public class Client {
    public static void main(String[] args) {
        Student jsj = new Student("jsj");
        OnlineCourse onlineCourse = new OnlineCourse();

        Student jdh = new Student("jdh");
        jdh.addPrivateCourse(onlineCourse);

        onlineCourse.addStudent(jsj);
        onlineCourse.changeState(OnlineCourse.State.PRIVATE);

        onlineCourse.addStudent(jdh);

        onlineCourse.addReview("hello", jsj);

        System.out.println(onlineCourse);
    }
}
