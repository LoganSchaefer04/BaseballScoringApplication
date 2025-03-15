package com.baseballscoringapplication.gameComponents;

import java.util.ArrayList;
import java.util.List;

public class Inning {
    private BaseballGame game; // Future idea for querying baseball games.
    int inningNumber; // Records inning number.
    boolean isTopInning; // Records if inning is top or bottom.
    Team battingTeam; // Batting team for current inning.
    Team fieldingTeam; // Fielding team for current inning.
    List<PlateAppearance> plateAppearanceList;
    int runnersLeftOnBase = 0; // Records how many runners were left on base at end of inning.
    int runsScored; // Records number of runs scored by batting team.
    int numberOfOuts; // Records number of outs in inning.
    Player currentPitcher; // Holds instance of current pitcher.

    public Inning(Player pitcher, Team batting, Team fielding, boolean isTopInning, int inningNumber) {
        //inningNumber = inningNum;
        this.isTopInning = isTopInning;
        this.inningNumber = inningNumber;
        battingTeam = batting;
        fieldingTeam = fielding;
        plateAppearanceList = new ArrayList<>();
        currentPitcher = pitcher;
        numberOfOuts = 0;
    }


    public void addNewPlateAppearance(PlateAppearance plateAppearance) {
        plateAppearanceList.add(plateAppearance);
    }

    public int getNumOuts() {
        return numberOfOuts;
    }

    public Player getNextBatter() {
        return battingTeam.getNextBatter();
    }

    public Player getCurrentPitcher() {
        return currentPitcher;
    }

    public void queueNextBatter() {
        battingTeam.queueNextBatter();
    }

    public void setNumberOfOuts(int outs) {
        numberOfOuts = outs;
    }

    public int getCurrentBatterSpot() {
        return battingTeam.getCurrentBatterIndex();
    }

    public int getRunsScored() {
        return runsScored;
    }
}
