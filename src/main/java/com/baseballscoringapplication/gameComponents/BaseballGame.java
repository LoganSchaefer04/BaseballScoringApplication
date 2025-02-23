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
    private ArrayList<Inning> listOfInnings; // Contains list of all instances of inning in game.

    // I want this line to go somewhere else. This does not belong here.
    // No reason for this to be in every single game that is stored in GameRepository.
    private Map<String, Team> listOfExistingTeams; // Contains list of all possible games.
    private Player homeStartingPitcher;
    private Player awayStartingPitcher;

    /**
     * Constructor for baseball game.
     * Leaving this basic constructor here in anticipation of future changes.
     */
    public BaseballGame() {
        homeTeam = new Team();
        awayTeam = new Team();
        listOfInnings = new ArrayList<>();
        listOfExistingTeams = new HashMap<>();
        setExistingTeams();
    }

    /**
     * Adds newly created Inning to the list of innings.
     *
     * @param inning currentInning being recorded in GameManager.
     */
    public void addNewHalfInning(Inning inning) {
        listOfInnings.add(inning);
    }


    /**
     * Sets the away team for baseball game.
     *
     * @param awayTeam team to be set as away team.
     */
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = listOfExistingTeams.get(awayTeam);
        System.out.println("Set the away team to " + listOfExistingTeams.get(awayTeam).getTeamName());
    }

    /**
     * Sets the home team for baseball game.
     *
     * @param homeTeam team to be set as home team.
     */
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

    public Map<String, Team> getExistingTeams() {
        return listOfExistingTeams;
    }

    /**
     * Adds all teams in TeamsFolder to listOfExistingTeams for selection.
     */
    private void setExistingTeams() {
        File teamsFolder = new File("src/main/resources/TeamsFolder");
        File[] listOfFiles = teamsFolder.listFiles();

        // Iterate over all files in the TeamsFolder and all teams to listOfExistingTeams.
        for (File file : listOfFiles) {
            listOfExistingTeams.put(file.getName(), new Team(file));
        }
    }

    /**
     * Calls for batting order class to handle setting batting order logic for home team.
     *
     * @param battingOrder a length 9 array containing which players should be in the batting order.
     */
    public void setHomeBattingOrder(List<String> battingOrder) {
        homeTeam.setBattingOrder(battingOrder);

    }

    /**
     * Calls for batting order class to handle setting batting order logic for away team.
     *
     * @param battingOrder a length 9 array containing which players should be in the batting order.
     */
    public void setAwayBattingOrder(List<String> battingOrder) {
        awayTeam.setBattingOrder(battingOrder);
    }

    // Return a specific inning from listOfInnings, likely for editing.
    public Inning getInning(int index) {
        return listOfInnings.get(index);
    }

}
