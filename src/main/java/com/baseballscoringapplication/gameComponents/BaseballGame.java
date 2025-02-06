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
    public BaseballGame() {
        homeTeam = new Team();
        awayTeam = new Team();
        listOfInnings = new ArrayList<>();
        listOfExistingTeams = new HashMap<>();
        setExistingTeams();
    }

    public void addNewInning(Inning inning) {
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
















































/*
    private void userIntroduction() {
        int userChoice = 0;
        Scanner userInput = new Scanner(System.in);

        while (userChoice != 1) {
            System.out.println("Please select where you would like to go.");
            System.out.println("1) Begin Game");
            System.out.println("2) Edit Home Team");
            System.out.println("3) Edit Away Team");
            System.out.println("4) Game Settings");
            userChoice = userInput.nextInt();

            switch(userChoice) {
                case 2:
                    homeTeam = setTeam();
                    break;
                case 3:
                    awayTeam = setTeam();
                    break;
            }
        }
    }

    private Team setTeam() {
        int userChoice = 0;
        Scanner setTeamInput = new Scanner(System.in);
        System.out.println("Would you like to use an existing team or use a generic team");
        System.out.println("1) Use Existing Team");
        System.out.println("2) Use Generic Team");
        System.out.println("3) Go Back");
        userChoice = setTeamInput.nextInt();
        switch(userChoice) {
            case 1:
                return teamSelect();
            case 2:
                System.out.println("Unimplemented");
            case 3:
                return null;
        }
        return null;
    }

    private void getExistingTeams() {
        File teamsFolder = new File("src/main/resources/TeamsFolder");
        File[] listOfFiles = teamsFolder.listFiles();

        for (File file : listOfFiles) {
            listOfExistingTeams.put(new Team(file));
            System.out.println("finished one team");
        }
    }

    private Team teamSelect() {
        int count = 1;
        System.out.println("Please Select From the Following Teams");
        Scanner teamSelectInput = new Scanner(System.in);
        for (Team team : listOfExistingTeams) {
            System.out.println(count + ") " + team.getTeamName());
        }
        int userChoice = teamSelectInput.nextInt();
        Team selectedTeam = listOfExistingTeams.get(userChoice - 1);
        System.out.println("You have selected the " + selectedTeam.getTeamName());
        selectedTeam.setBattingOrder();
        return selectedTeam;
    }
    private void beginGame() {
        for (int i = 0; i < 9; i++) {
            listOfInnings.add(new Inning(awayTeam, homeTeam));
            listOfInnings.add(new Inning(homeTeam, awayTeam));
        }
    }

 */

}
