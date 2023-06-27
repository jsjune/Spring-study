package com.example.designpattern.behavioral_patterns.state._02_after;


public class Client {
    public static void main(String[] args) {
        Student jsj = new Student("jsj");
        OnlineCourse onlineCourse = new OnlineCourse();

        Student jdh = new Student("jdh");
        jdh.addPrivate(onlineCourse);

        onlineCourse.addStudent(jsj);
        onlineCourse.changeState(new Private(onlineCourse));

        onlineCourse.addStudent(jdh);

        onlineCourse.addReview("hello", jsj);

        System.out.println(onlineCourse.getState());
        System.out.println(onlineCourse.getReviews());
        System.out.println(onlineCourse.getStudents());
    }
}
