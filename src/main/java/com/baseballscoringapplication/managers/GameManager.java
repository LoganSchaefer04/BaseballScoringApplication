package com.baseballscoringapplication.managers;

import com.baseballscoringapplication.gameComponents.BaseballGame;
import com.baseballscoringapplication.gameComponents.Inning;
import com.baseballscoringapplication.gameComponents.PlateAppearance;
import com.baseballscoringapplication.gameComponents.Team;

import java.util.List;
import java.util.Map;

public class GameManager {
    private static GameManager instance;
    private BaseballGame currentGame;
    private Inning currentInning;
    private PlateAppearance currentPlateAppearance;
    public boolean isTopInning;

    public static GameManager getInstance() {
        return instance;
    }

    public GameManager() {
        this.currentGame = null;
    }

    public void createGame() {
        this.currentGame = new BaseballGame();
        isTopInning = true;
        System.out.println("createGame called");
    }

    public Map<String, Team> getAllTeams() {
        return currentGame.getExistingTeams();
    }

    public void setGameHomeTeam(String team) {
        currentGame.setHomeTeam(team);
    }
    public void setGameAwayTeam(String team) {
        currentGame.setAwayTeam(team);
    }

    public Team getHomeTeam() {
        return currentGame.getHomeTeam();
    }

    public Team getAwayTeam() {
        return currentGame.getAwayTeam();
    }

    public void setHomeBattingOrder(List<String> battingOrder) {
        currentGame.setHomeBattingOrder(battingOrder);
    }
    public void setAwayBattingOrder(List<String> battingOrder) {
        currentGame.setAwayBattingOrder(battingOrder);
    }

    public int getStrikeCount() {
        return currentPlateAppearance.getStrikeCount();
    }

    public void scoreGame() {
        System.out.println("scoreGame is creating first inning and first plate appearance");
        startNewInning();
        startNewPlateAppearance();
        //currentInning = new Inning(currentGame.getAwayTeam(), currentGame.getHomeTeam(), isTopInning);
        //currentPlateAppearance = new PlateAppearance(currentInning.getNextBatter());
    }

    private void startNewInning() {
        Inning inning = new Inning(currentGame.getAwayTeam(), currentGame.getHomeTeam(), isTopInning);
        currentGame.addNewInning(inning);
        this.currentInning = inning;
    }

    private void startNewPlateAppearance() {
        currentPlateAppearance = new PlateAppearance(currentInning.getNextBatter());
        currentInning.queueNextBatter();
        System.out.println("New Batter: " + currentPlateAppearance.getBatter().getPlayerName());
        currentInning.addNewPlateAppearance(currentPlateAppearance);
    }

    public int scoreStrike() {
        currentPlateAppearance.scoreStrike();
        if (currentPlateAppearance.getStrikeCount() == 3) {
            startNewPlateAppearance();
            return 1;
        }
        return 0;
    }

    private void checkStrikes() {
        // TODO: Implement
    }

    private void checkBalls() {
        // TODO: Implement
    }
}






