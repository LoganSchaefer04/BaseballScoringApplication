package com.baseballscoringapplication.gameComponents;

import java.util.ArrayList;

// 54 atBat instances will be created throughout a game, saving information for viewing
//
public class PlateAppearance {
    Player pitcher;
    Player batter;
    private int ballCount = 0;
    private int outsRecorded = 0;
    private int strikeCount = 0;
    private Play play;
    ArrayList<Pitch> pitchesList;

    public PlateAppearance(Player batter) {
        this.batter = batter;
        this.pitcher = (new Player());
        play = null;
        pitchesList = new ArrayList<>();
    }

    public PlateAppearance (Player batter, Player pitcher) {
        this.pitcher = pitcher;
        this.batter = batter;
        pitchesList = new ArrayList<>();
        play = null;
        System.out.println("New Plate Appearance: Pitcher: " + pitcher.getPlayerName() + ". Batter: " + batter.getPlayerName());
        //beginPlateAppearance(outs, basePaths);
    }
    public void printCount() {
        System.out.println("Balls: " + ballCount);
        System.out.println("Strikes: " + strikeCount);
    }

    public int scoreStrike(Pitch pitch) {
        pitchesList.add(pitch);
        return ++strikeCount;
    }

    public int scoreBall(Pitch pitch) {
        pitchesList.add(pitch);
        return ++ballCount;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public Player getBatter() {
        return batter;
    }



}
