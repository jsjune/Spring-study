package com.example.designpattern.creational_patterns.builder;

public class App {
    public static void main(String[] args) {
        TourDirector director = new TourDirector(new DefaultTourBuilder());
        TourPlan cancunTrip = director.cancunTrip();
        TourPlan longBeachTrip = director.longBeachTrip();

        System.out.println("cancunTrip = " + cancunTrip);
        System.out.println("longBeachTrip = " + longBeachTrip);
    }
}
