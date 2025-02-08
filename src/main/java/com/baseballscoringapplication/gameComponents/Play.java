package com.baseballscoringapplication.gameComponents;

import java.util.ArrayList;
import java.util.Scanner;

public class Play {
    int strikeCount;
    int ballCount;
    // Pop-up, ground ball, line drive, fly ball, etc.
    String contactType;
    String contactResult;
    ArrayList<Integer> defensiveSequence;
    int outs = 0;
    ArrayList<Integer> runnersAdvancement;
    ArrayList<Player> runnersScored;
    final String[] defendersList = {"P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF"};

    public Play(String contactType) {
        this.contactType = contactType;
    }

    public void scoreRun(Player player) {
        if (runnersScored == null) {
            runnersScored = new ArrayList<>();
        }
        runnersScored.add(player);
    }




    public int getNumOuts() {
        return 1;
    }

    public ArrayList<Integer> getRunnersAdvancement() {
        return runnersAdvancement;
    }

}
