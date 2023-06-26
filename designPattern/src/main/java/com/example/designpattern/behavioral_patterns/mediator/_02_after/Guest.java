package com.example.designpattern.behavioral_patterns.mediator._02_after;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Guest {
    private Integer id;
    private FrontDesk frontDesk = new FrontDesk();

    public void getTowers(int numbersOfTowers) {
        this.frontDesk.getTowers(this, numbersOfTowers);
    }

    public void dinner(LocalDateTime dateTime) {
        this.frontDesk.dinner(this, dateTime);

    }

}
