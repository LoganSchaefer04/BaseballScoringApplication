package com.baseballscoringapplication.managers;

import com.baseballscoringapplication.gameComponents.*;

import java.util.List;
import java.util.Map;

public class GameManager {
    private static GameManager instance;
    private BasePathManager basePathManager = new BasePathManager(this);
    private BaseballGame currentGame;
    private Inning currentInning;
    private PlateAppearance currentPlateAppearance;
    private Play currentPlay;
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

    public int getBallCount() {
        return currentPlateAppearance.getBallCount();
    }

    public BasePathManager getBasePathManager() {
        return basePathManager;
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
        currentInning.addNewPlateAppearance(currentPlateAppearance);
    }

    public int scoreStrike() {
        Pitch pitch = new Pitch("Strike", getStrikeCount());
        if (currentPlateAppearance.scoreStrike(pitch) == 3) {
            startNewPlateAppearance();
            return 1;
        }
        return 0;
    }

    public int scoreBall() {
        Pitch pitch = new Pitch("Ball", getStrikeCount());
        if (currentPlateAppearance.scoreBall(pitch) == 4) {
            //basePathManager.scoreWalk(currentPlateAppearance.getBatter());
            scoreWalk();
            startNewPlateAppearance();
            return 1;
        }
        return 0;
    }

    public void scoreWalk() {
        currentPlay = new Play("Walk");
        basePathManager.scoreWalk(currentPlateAppearance.getBatter());
    }

    public void scoreRun(Player player) {
        currentPlay.scoreRun(player);
    }
}






