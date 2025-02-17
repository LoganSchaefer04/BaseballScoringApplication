package com.baseballscoringapplication.gameComponents;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class BaseballGame {
    private Team homeTeam;
    private Team awayTeam;
    private int numOfInnings = 0;
    private int homeTeamRuns = 0;
    private int awayTeamRuns = 0;
    private ArrayList<Inning> listOfInnings;
    private Map<String, Team> listOfExistingTeams;
    private Player homeStartingPitcher;
    private Player awayStartingPitcher;
    public BaseballGame() {
        homeTeam = new Team();
        awayTeam = new Team();
        listOfInnings = new ArrayList<>();
        listOfExistingTeams = new HashMap<>();
        setExistingTeams();
    }

    public void addNewHalfInning(Inning inning) {
        listOfInnings.add(inning);
    }



    public void setAwayTeam(String awayTeam) {
        this.awayTeam = listOfExistingTeams.get(awayTeam);
        System.out.println("Set the away team to " + listOfExistingTeams.get(awayTeam).getTeamName());
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = listOfExistingTeams.get(homeTeam);
        System.out.println("Set the home team to " + listOfExistingTeams.get(homeTeam).getTeamName());
    }

    public void setHomeTeamRuns(int runs) {
        homeTeamRuns = runs;
    }

    public int getHomeTeamRuns() {
        return homeTeamRuns;
    }

    public void setHomeStartingPitcher(Player pitcher) {
        homeStartingPitcher = pitcher;
    }

    public Player getHomeStarter() {
        return homeStartingPitcher;
    }

    public void setAwayStartingPitcher(Player pitcher) {
        awayStartingPitcher = pitcher;
    }
    public Player getAwayStarter() {
        return awayStartingPitcher;
    }
    public void setAwayTeamRuns(int runs) {
        awayTeamRuns = runs;
    }

    public int getAwayTeamRuns() {
        return awayTeamRuns;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getNumOfInnings() {
        return numOfInnings;
    }

    private void setExistingTeams() {
        File teamsFolder = new File("src/main/resources/TeamsFolder");
        File[] listOfFiles = teamsFolder.listFiles();

        for (File file : listOfFiles) {
            listOfExistingTeams.put(file.getName(), new Team(file));
        }
    }

    public Map<String, Team> getExistingTeams() {
        return listOfExistingTeams;
    }

    public void setHomeBattingOrder(List<String> battingOrder) {
        homeTeam.setBattingOrder(battingOrder);

    }

    public void setAwayBattingOrder(List<String> battingOrder) {
        awayTeam.setBattingOrder(battingOrder);
    }

    public Inning getInning(int index) {
        return listOfInnings.get(index);
    }

}
