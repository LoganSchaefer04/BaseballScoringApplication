package com.baseballscoringapplication;

import com.baseballscoringapplication.gameComponents.Play.Play;

import java.util.ArrayList;

public class GameStats {
    ArrayList<Play> outcomeList;

    public GameStats() {
        outcomeList = new ArrayList<>();
    }

    public int getNumberOfAtBats() {
        int numberOfAtBats = 0;
        for (int i = 0; i < outcomeList.size(); i++) {
            if (outcomeList.get(i).isAtBat()) {
                numberOfAtBats++;
            }
        }
        System.out.println("number of at bats: " + numberOfAtBats);
        return numberOfAtBats;
    }

    public int getNumberOfHits() {
        int numberOfHits = 0;
        for (int i = 0; i < outcomeList.size(); i++) {
            if (outcomeList.get(i).isHit()) {
                numberOfHits++;
            }
        }
        System.out.println("Number of hits: " + numberOfHits);
        return numberOfHits;
    }

    public void addOutcome(Play play) {
        outcomeList.add(play);
        System.out.println("Added outcome");
    }
}
