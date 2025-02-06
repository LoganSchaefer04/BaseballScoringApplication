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
        //Scanner teamNameInput = new Scanner(System.in);
        //System.out.println("Please enter a team: ");
        //teamName = teamNameInput.next();
        //System.out.println("Calling to create roster");
        //teamRoster = new Roster(26);
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

    public Player getNextBatter() {
        return battingOrder.getNextPlayer();
    }

    public void setBattingOrder(List<String> newBattingOrder) {
        battingOrder.setBattingOrder(newBattingOrder, teamRoster.getRosterMap());
    }

    public void queueNextBatter() {
        battingOrder.queueNextBatter();
    }
}
