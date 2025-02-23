package com.baseballscoringapplication.gameComponents;

import java.util.ArrayList;

// 54 atBat instances will be created throughout a game, saving information for viewing and querying.
public class PlateAppearance {

    // In the future, this class will likely need to change to handle pinch hitters and pitching changes.
    Player pitcher; // pitcher for current plate appearance.
    Player batter; // Batter for current plate appearance.
    private int ballCount = 0;
    private int outsRecorded = 0;
    private int strikeCount = 0;
    private Play play; // Each plate appearance holds an instance of a play for how appearance ends.
    ArrayList<Pitch> pitchesList; // List of all pitches in order during plate appearance.

    /**
     * Creates new plate appearance.
     * This needs to be deleted. Need to use constructor with pitcher and batter parameters.
     *
     * @param batter Batter taking the plate appearance.
     */
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

    /**
     * Score a strike in the plate appearance.
     *
     * @param pitch Pitch to add to list of pitches.
     * @return The number of strikes in the plate appearance.
     */
    public int scoreStrike(Pitch pitch) {
        pitchesList.add(pitch);
        return ++strikeCount;
    }

    /**
     * Score a strike in the plate appearance.
     *
     * @param pitch Pitch to add to list of pitches.
     * @return The number of balls in the plate appearance.
     */
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

    public void setPlay(Play play) {
        this.play = play;
    }

    public Play getPlay() {
        return play;
    }



}
