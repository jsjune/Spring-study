package com.example.designpattern.behavioral_patterns.mediator._02_after;

import java.time.LocalDateTime;

public class FrontDesk {
    private CleaningService cleaningService = new CleaningService();
    private Restaurant restaurant = new Restaurant();

    public void getTowers(Guest guest, int numberOfTowers) {
        cleaningService.getTowers(guest.getId(), numberOfTowers);
    }

    public String getRoomNumberFor(Integer guestId) {
        return "1111";
    }

    public void dinner(Guest guest, LocalDateTime localDateTime) {
        restaurant.dinner(guest);

    }
}
