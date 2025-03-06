package com.baseballscoringapplication.gameComponents;

import com.baseballscoringapplication.DefensiveLineup;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team {
    String teamName;
    BattingOrder battingOrder; // Instance of batting order for each team
    DefensiveLineup defensiveLineup; // Instance of specific defensive lineup during game
    Roster teamRoster; // Instance of roster for each team

    /**
     * Constructor for custom team creation
     *
     * @param file text file to be read in
     */
    public Team(File file) {
        // Set team name and create necessary instances.
        try {
            Scanner teamFileReader = new Scanner(file);
            teamName = file.getName(); // Set team name to text file name
            teamRoster = new Roster(file); // Adds all players in text file to roster
            battingOrder = new BattingOrder(teamRoster); // Generic batting order.
            defensiveLineup = new DefensiveLineup();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Generic Team, only 9 players, 1 pitcher.
     */
    public Team() {
        teamName = "Generic Team";
        teamRoster = new Roster();
        battingOrder = new BattingOrder(teamRoster);
    }

    public void setDefensiveLineup(String[] playersList) {
        defensiveLineup.setLineup(playersList, teamRoster.getRosterMap());
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
