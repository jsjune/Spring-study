package com.example.designpattern.behavioral_patterns.memento._02_after;

import lombok.Getter;

@Getter
public class GameSave {
    private final int blueTeamScore;
    private final int redTeamScore;

    public GameSave(int blueTeamScore, int redTeamScore) {
        this.blueTeamScore = blueTeamScore;
        this.redTeamScore = redTeamScore;
    }
}
