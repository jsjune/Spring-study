package com.example.designpattern.behavioral_patterns.mediator._01_before;

public class CleaningService {
    public void clean(Gym gym) {
        System.out.println("Clean " + gym);
    }

    public void clean(Restaurant restaurant) {
        System.out.println("clean " + restaurant);
    }

    public void getTower(Guest guest, int numberOfTower) {
        System.out.println(numberOfTower + " towers to " + guest);
    }

    public void dinner() {

    }
}
