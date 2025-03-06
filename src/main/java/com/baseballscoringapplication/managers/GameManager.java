package com.baseballscoringapplication.managers;

import com.baseballscoringapplication.gameComponents.*;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Map;

public class GameManager {
    private BasePathManager basePathManager = new BasePathManager(this); // Handles base path logic.
    private BaseballGame currentGame; // Current game that is being updated
    private Inning currentHalfInning; // Current inning that is being updated
    public int currentInningNumber = 1; // Used as value for UI
    public int currentHalfInningNumber = 0; // Used for going back to previous innings for editing
    private PlateAppearance currentPlateAppearance; // current plate appearance that is being updated
    private Play currentPlay; // current play that is being updated
    public boolean isTopInning = true; // Used specifically to set starting pitcher in the bottom of the first.
    public boolean isFirstInning = true; // Keeps track of if it is the top or bottom of inning


    /**
     * Set currentGame to null until game is created.
     */
    public GameManager() {
        this.currentGame = null;
    }


    // Create instance of BaseballGame
    public void createGame() {
        this.currentGame = new BaseballGame();
    }

    /**
     * Update game state with starting pitchers.
     *
     * @param homeTeamStarter pitcher that will start for home team.
     * @param awayTeamStarter pitcher that will start for away team.
     */
    public void setStartingPitchers(String homeTeamStarter, String awayTeamStarter) {
        // Update game starters with inputs given by UI.
        currentGame.setHomeStartingPitcher(currentGame.getHomeTeam().getPlayer(homeTeamStarter));
        currentGame.setAwayStartingPitcher(currentGame.getAwayTeam().getPlayer(awayTeamStarter));
    }

    /**
     * Begin recording game
     */
    public void scoreGame() {
        // Create first inning, add to list of innings.
        Inning newHalfInning = new Inning(currentGame.getHomeStarter(), currentGame.getAwayTeam(), currentGame.getHomeTeam(), isTopInning, currentInningNumber);
        currentGame.addNewHalfInning(newHalfInning);
        currentHalfInning = newHalfInning;

        // Create first plate appearance.
        startNewPlateAppearance();
    }

    /**
     * Create new inning and update game state
     */
    private void startNewHalfInning() {
        // Clear the bases.
        basePathManager.clearBases();

        // Check if current inning is the first half inning in the game.
        if (isFirstInning) {
            // Update game state to be bottom inning and not the first inning.
            isTopInning = false;
            isFirstInning = false;

            // Create new inning with newly updated information.
            // Add new inning to list of innings
            // Set newly created inning to current inning
            Inning newHalfInning = new Inning(currentGame.getAwayStarter(), currentGame.getHomeTeam(), currentGame.getAwayTeam(), isTopInning, currentInningNumber);
            currentGame.addNewHalfInning(newHalfInning);
            currentHalfInning = newHalfInning;
            currentHalfInningNumber++;
            return;
        }

        // Check if current inning is the top of inning.
        if (isTopInning) {
            // Create new inning where the home team is batting, away team is on defense
            isTopInning = false;
            Inning newHalfInning = new Inning(currentGame.getInning(currentHalfInningNumber - 1).getCurrentPitcher(), currentGame.getHomeTeam(), currentGame.getAwayTeam(), isTopInning, currentInningNumber);

            // Add new inning to list of innings.
            // Set newly created inning to current half inning.
            // Increment inning counter.
            currentGame.addNewHalfInning(newHalfInning);
            currentHalfInning = newHalfInning;
            currentHalfInningNumber++;
        } else {
            // Create new inning where the away team is batting, home team is on defense.
            // Set inning to current inning.
            // Increment half inning and inning count.
            isTopInning = true;
            Inning newHalfInning = new Inning(currentGame.getInning(currentHalfInningNumber - 1).getCurrentPitcher(), currentGame.getAwayTeam(), currentGame.getHomeTeam(), isTopInning, currentInningNumber);
            currentGame.addNewHalfInning(newHalfInning);
            currentHalfInning = newHalfInning;
            currentInningNumber++;
            currentHalfInningNumber++;
        }
    }

    /**
     * Start a new plate appearance
     */
    private void startNewPlateAppearance() {
        // Create new plate appearance with next batter in batting order.
        currentPlateAppearance = new PlateAppearance(currentHalfInning.getNextBatter());

        // Queue index in batting order to return next player in batting order.
        // Add newly created plate appearance to list of plate appearances in inning.
        currentHalfInning.queueNextBatter();
        currentHalfInning.addNewPlateAppearance(currentPlateAppearance);
    }

    /**
     *  Record a ball in the plate appearance and update game state
     */
    public void scoreStrike() {
        // Create a new pitch and add to pitch recorder.
        Pitch pitch = new Pitch("Strike", getStrikeCount());
        currentHalfInning.getCurrentPitcher().addPitch(pitch);

        // Check if pitch is third strike.
        if (currentPlateAppearance.scoreStrike(pitch) == 3) {
            // Score a strikeout
            scoreStrikeout();

        }
    }

    /**
     * Record a ball in the current plate appearance and update game state.
     */
    public void scoreBall() {
        // Create new pitch and add to pitch recorder.
        Pitch pitch = new Pitch("Ball", getStrikeCount());
        currentHalfInning.getCurrentPitcher().addPitch(pitch);

        // Check if pitch is fourth ball.
        if (currentPlateAppearance.scoreBall(pitch) == 4) {
            // Score a walk and start a new plate appearance
            scoreWalk();
            startNewPlateAppearance();
        }
    }

    public void scoreFoulBall() {
        Pitch pitch = new Pitch("Foul Ball", getStrikeCount());
        currentHalfInning.getCurrentPitcher().addPitch(pitch);
        currentPlateAppearance.scoreFoulBall(pitch);
    }

    /**
     * Score a strikeout and update game state
     */
    public void scoreStrikeout() {
        // Create play labeled as strikeout
        // Set plate appearance play to newly created play
        currentPlay = new Play("Strikeout");
        currentPlateAppearance.setPlay(currentPlay);

        // Increment outs in inning
        // Increment number of outs recorded by pitcher
        currentHalfInning.setNumberOfOuts(currentHalfInning.getNumOuts() + 1);
        currentHalfInning.getCurrentPitcher().addOutsRecorded(1);

        // Check if inning is over
        // Start new plateAppearance
        checkNewInning();
        startNewPlateAppearance();
    }

    /**
     * Score a walk and update game state
     */
    public void scoreWalk() {
        // Create play for walk, and add to current plate appearance.
        currentPlay = new Play("Walk");
        currentPlateAppearance.setPlay(currentPlay);

        // Update base paths in basePathManager.
        basePathManager.scoreWalk(currentPlateAppearance.getBatter());
    }

    /**
     * Score a run to battingTeam
     *
     * @param player used to record the player that scored the run
     */
    public void scoreRun(Player player) {
        currentPlay.scoreRun(player);
        if (isTopInning) {
            currentGame.setAwayTeamRuns(currentGame.getAwayTeamRuns() + 1);
        } else {
            currentGame.setHomeTeamRuns(currentGame.getHomeTeamRuns() + 1);
        }

    }

    /**
     * Check if inning is over
     */
    public void checkNewInning() {
        if (currentHalfInning.getNumOuts() == 3) {
            startNewHalfInning();
        }
    }

    public void setDefense(Team team, String[] playersList) {
        team.setDefensiveLineup(playersList);
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
}





