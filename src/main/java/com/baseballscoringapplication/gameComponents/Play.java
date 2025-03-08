package com.baseballscoringapplication.gameComponents;

import java.util.ArrayList;
import java.util.Scanner;

public class Play {
    int strikeCount;
    int ballCount;
    // Pop-up, ground ball, line drive, fly ball, etc.
    String contactType;
    String contactResult;
    private boolean isAtBat;
    private boolean isHit;
    ArrayList<Integer> defensiveSequence;
    int outs = 0;
    ArrayList<Integer> runnersAdvancement;
    ArrayList<Player> runnersScored;
    Inning inning;
    final String[] defendersList = {"P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF"};

    public Play(String contactType, Inning inning) {
        this.contactType = contactType;
        this.inning = inning;

        switch (contactType) {
            case "Walk":
                isAtBat = false;
                break;
            case "Strikeout":
                isAtBat = true;
                isHit = false;
        }

    }

    public void scoreRun(Player player) {
        if (runnersScored == null) {
            runnersScored = new ArrayList<>();
        }
        runnersScored.add(player);

    }



    public boolean isAtBat() {
        return isAtBat;
    }

    public boolean isHit() {
        return isHit;
    }
    public int getNumOuts() {
        return 1;
    }

    public ArrayList<Integer> getRunnersAdvancement() {
        return runnersAdvancement;
    }

}
