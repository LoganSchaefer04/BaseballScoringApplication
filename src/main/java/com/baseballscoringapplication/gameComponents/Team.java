package com.baseballscoringapplication.gameComponents;

import com.baseballscoringapplication.DefensiveLineup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team {
    String teamName;
    BattingOrder battingOrder;
    DefensiveLineup defensiveLineup;
    Roster teamRoster;

    public Team(File file) {
        try {
            Scanner teamFileReader = new Scanner(file);
            teamName = file.getName();
            teamRoster = new Roster(file);
            battingOrder = new BattingOrder(teamRoster);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public Team() {
        teamName = "Generic Team";
        teamRoster = new Roster();
        battingOrder = new BattingOrder(teamRoster);
    }



    public ArrayList<Player> getBattingOrder() {
        return battingOrder.getBattingOrder();
    }
    public String getTeamName() {
        return teamName;
    }

    public ArrayList<Player> getRoster() {
        return teamRoster.getRoster();
    }
    public Player getPlayer(String playerName) {
        return teamRoster.getPlayer(playerName);
    }

    public Player getNextBatter() {
        return battingOrder.getNextPlayer();
    }

    public int getCurrentBatterIndex() {
        return battingOrder.nextBatterUpIndex;
    }

    public void setBattingOrder(List<String> newBattingOrder) {
        battingOrder.setBattingOrder(newBattingOrder, teamRoster.getRosterMap());
    }

    public void queueNextBatter() {
        battingOrder.queueNextBatter();
    }
}
