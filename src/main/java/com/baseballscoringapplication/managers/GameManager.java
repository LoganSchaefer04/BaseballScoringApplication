package com.baseballscoringapplication.managers;

import com.baseballscoringapplication.gameComponents.*;

import java.util.List;
import java.util.Map;

public class GameManager {
    private static GameManager instance;
    private BasePathManager basePathManager = new BasePathManager(this);
    private BaseballGame currentGame;
    private Inning currentHalfInning;
    public int currentInningNumber = 1;
    public int currentHalfInningNumber = 0;
    private PlateAppearance currentPlateAppearance;
    private Play currentPlay;
    public boolean isTopInning;
    public boolean isFirstInning = true;

    public static GameManager getInstance() {
        return instance;
    }

    public GameManager() {
        this.currentGame = null;
    }

    public void createGame() {
        this.currentGame = new BaseballGame();
        isTopInning = true;
    }

    public void setStartingPitchers(String homeTeamStarter, String awayTeamStarter) {
        System.out.println("Home team starter: " + homeTeamStarter);
        System.out.println("Away team starter: " + awayTeamStarter);
        currentGame.setHomeStartingPitcher(currentGame.getHomeTeam().getPlayer(homeTeamStarter));
        currentGame.setAwayStartingPitcher(currentGame.getAwayTeam().getPlayer(awayTeamStarter));
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

    public String getHomeTeamRuns() {
        return Integer.toString(currentGame.getHomeTeamRuns());
    }

    public String getAwayTeamRuns() {
        return Integer.toString(currentGame.getAwayTeamRuns());
    }

    public int getNumOuts() {
        return currentHalfInning.getNumOuts();
    }
    public Player getCurrentPitcher() {
        return currentHalfInning.getCurrentPitcher();
    }
    public Player getCurrentBatter() {
        return currentPlateAppearance.getBatter();
    }
    public String getCurrentBatterSpot() {
        return Integer.toString(currentHalfInning.getCurrentBatterIndex());
    }

    public void scoreGame() {
        Inning newHalfInning = new Inning(currentGame.getHomeStarter(), currentGame.getAwayTeam(), currentGame.getHomeTeam(), isTopInning, currentInningNumber);
        currentGame.addNewHalfInning(newHalfInning);
        currentHalfInning = newHalfInning;
        startNewPlateAppearance();
        //currentInning = new Inning(currentGame.getAwayTeam(), currentGame.getHomeTeam(), isTopInning);
        //currentPlateAppearance = new PlateAppearance(currentInning.getNextBatter());
    }

    private void startNewHalfInning() {
        System.out.println("Starting new half inning");
        basePathManager.clearBases();

        if (isFirstInning) {
            isTopInning = false;
            isFirstInning = false;
            Inning newHalfInning = new Inning(currentGame.getAwayStarter(), currentGame.getHomeTeam(), currentGame.getAwayTeam(), isTopInning, currentInningNumber);
            currentGame.addNewHalfInning(newHalfInning);
            currentHalfInning = newHalfInning;
            return;
        }
        if (isTopInning) {
            isTopInning = false;
            Inning newHalfInning = new Inning(currentGame.getInning(currentHalfInningNumber).getCurrentPitcher(), currentGame.getHomeTeam(), currentGame.getAwayTeam(), isTopInning, currentInningNumber);
            currentGame.addNewHalfInning(newHalfInning);
            currentHalfInning = newHalfInning;
            currentHalfInningNumber++;
        } else {
            isTopInning = true;
            Inning newHalfInning = new Inning(currentGame.getInning(currentHalfInningNumber).getCurrentPitcher(), currentGame.getAwayTeam(), currentGame.getHomeTeam(), isTopInning, currentInningNumber);
            currentGame.addNewHalfInning(newHalfInning);
            currentHalfInning = newHalfInning;
            currentInningNumber++;
            currentHalfInningNumber++;
        }
    }

    private void startNewPlateAppearance() {
        currentPlateAppearance = new PlateAppearance(currentHalfInning.getNextBatter());
        currentHalfInning.queueNextBatter();
        currentHalfInning.addNewPlateAppearance(currentPlateAppearance);
    }

    public int scoreStrike() {
        Pitch pitch = new Pitch("Strike", getStrikeCount());
        currentHalfInning.getCurrentPitcher().addPitch(pitch);
        if (currentPlateAppearance.scoreStrike(pitch) == 3) {
            scoreStrikeout();
            return 1;
        }
        return 0;
    }

    public int scoreBall() {
        Pitch pitch = new Pitch("Ball", getStrikeCount());
        currentHalfInning.getCurrentPitcher().addPitch(pitch);
        if (currentPlateAppearance.scoreBall(pitch) == 4) {
            //basePathManager.scoreWalk(currentPlateAppearance.getBatter());
            scoreWalk();
            startNewPlateAppearance();
            return 1;
        }
        return 0;
    }

    public void scoreStrikeout() {
        currentPlay = new Play("Strikeout");
        currentPlateAppearance.setPlay(currentPlay);
        currentHalfInning.setNumberOfOuts(currentHalfInning.getNumOuts() + 1);
        currentHalfInning.getCurrentPitcher().addOutsRecorded(1);
        checkNewInning();
        startNewPlateAppearance();
    }

    public void scoreWalk() {
        currentPlay = new Play("Walk");
        currentPlateAppearance.setPlay(currentPlay);
        basePathManager.scoreWalk(currentPlateAppearance.getBatter());
    }

    public void scoreRun(Player player) {
        currentPlay.scoreRun(player);
        if (isTopInning) {
            currentGame.setAwayTeamRuns(currentGame.getAwayTeamRuns() + 1);
        } else {
            currentGame.setHomeTeamRuns(currentGame.getHomeTeamRuns() + 1);
        }

    }

    public void checkNewInning() {
        if (currentHalfInning.getNumOuts() == 3) {
            startNewHalfInning();
        }
    }
}






