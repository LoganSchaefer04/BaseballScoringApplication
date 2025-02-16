package com.baseballscoringapplication.gameComponents;

import java.util.ArrayList;
import java.util.List;

public class Inning {
    private BaseballGame game;
    int inningNumber;
    boolean isTopInning;
    Team battingTeam;
    Team fieldingTeam;
    List<PlateAppearance> plateAppearanceList;
    int runnersLeftOnBase = 0;
    int runsScored;
    int numberOfOuts;
    Player currentPitcher;

    public Inning(Player pitcher, Team batting, Team fielding, boolean isTopInning, int inningNumber) {
        //inningNumber = inningNum;
        this.isTopInning = isTopInning;
        this.inningNumber = inningNumber;
        battingTeam = batting;
        fieldingTeam = fielding;
        plateAppearanceList = new ArrayList<>();
        currentPitcher = pitcher;
        System.out.println("New Inning: Batting: " + batting.getTeamName());
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

    public int getCurrentBatterIndex() {
        return battingTeam.getCurrentBatterIndex();
    }
































/*
    public void beginInning() {
        int[] basePaths = {0, 0, 0};
        int outs = 0;
        while (outs != 3) {
            Player batter = battingTeam.getBattingOrder().getNextPlayer();
            Player pitcher = new Player();
            PlateAppearance plateAppearance = new PlateAppearance(pitcher, batter, outs, basePaths);
            plateAppearanceList.add(plateAppearance);
            outs += plateAppearance.getPlay().getNumOuts();
            runsScored += plateAppearance.getPlay().getRunsScored();
        }
    }

 */

    public int getRunsScored() {
        return runsScored;
    }
}
