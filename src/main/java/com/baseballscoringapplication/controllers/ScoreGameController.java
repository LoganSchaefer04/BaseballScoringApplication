package com.baseballscoringapplication.controllers;

import com.baseballscoringapplication.managers.BasePathManager;
import com.baseballscoringapplication.managers.GameManager;
import com.baseballscoringapplication.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

public class ScoreGameController {
    @FXML
    private VBox pitchSelectionVBox; // Contains buttons for recording pitches
    @FXML
    private HBox outsHBox; // Contains circles that track number of outs.
    @FXML
    private HBox ballsHBox; // Contains circles that track number of balls.
    @FXML
    private HBox strikesHBox; // Contains circles that track number of strikes.
    @FXML
    private Rectangle firstBase; // Used to track first base occupancy
    @FXML
    private Rectangle secondBase; // Used to track second base occupancy
    @FXML
    private Rectangle thirdBase; // Used to track third base occupancy
    @FXML
    private Button firstBaseRunner; // Button used for scorekeeper to record advancements.
    @FXML
    private Button secondBaseRunner; // Button used for scorekeeper to record advancements.
    @FXML
    private Button thirdBaseRunner; // Button used for scorekeeper to record advancements.
    @FXML
    private Label awayTeamNameLabel; // Label for scoreboard.
    @FXML
    private Label homeTeamNameLabel; // Label for scoreboard.
    @FXML
    private Label awayTeamScoreLabel; // Tracks number of runs away team has scored.
    @FXML
    private Label homeTeamScoreLabel; // Tracks number of runs home team has scored.
    @FXML
    private Button pitcherButton; // Used for pitching swaps and recording defensive plays.
    @FXML
    private Label pitcherLabel; // Used as title for table of pitcher stats.
    @FXML
    private Label currentPitcherStatsLabel; // Holds key information about a pitcher.
    @FXML
    private Label batterLabel; // Used as a title for table of hitter stats.
    @FXML
    private Label currentBatterStatsLabel; // Holds key information about a batter.
    @FXML
    private Polygon topInningArrow; // Arrow that tracks if is the top of an inning.
    @FXML
    private Polygon bottomInningArrow; // Arrow that tracks if is the bottom of an inning.
    @FXML
    private Label currentInningLabel;
    private BasePathManager basePathManager; // Instance of basePathmManager to keep track of basepaths.

    private GameManager gameManager; // Used to update game state from inputs
    private SceneManager sceneManager; // Used for scene swapping

    /**
     * Initializes all required UI components to properly display when scene is loaded.
     */
    public void initialize() {
        awayTeamNameLabel.setText(gameManager.getAwayTeam().getTeamName()); // Set away team in scoreboard.
        homeTeamNameLabel.setText(gameManager.getHomeTeam().getTeamName()); // Set home team in scoreboard.
        awayTeamScoreLabel.setText(gameManager.getAwayTeamRuns()); // Set number of runs away team has scored.
        homeTeamScoreLabel.setText(gameManager.getHomeTeamRuns()); // Set number of runs home team has scored.
        pitcherButton.setText(gameManager.getCurrentPitcher().getPlayerName()); // Set pitcher.
        pitcherLabel.setText("P: " + gameManager.getCurrentPitcher().getPlayerName()); // Set pitcher.
        currentPitcherStatsLabel.setText("P: 0\nIP: 0\nER: 0"); // Initialize pitcher stats to empty.
        batterLabel.setText("1. " + gameManager.getCurrentBatter().getPlayerName()); // Show lead off batter.
        currentBatterStatsLabel.setText("0-0"); // Set lead off batter's stats to 0-0.
        bottomInningArrow.setVisible(false); // Set arrows to show top of inning.
        currentInningLabel.setTextAlignment(TextAlignment.CENTER);
        currentInningLabel.setText("1"); // Initialize first inning

    }

    /**
     * Creates new instance of ScoreGameController with specified gameManager and sceneManager.
     *
     * @param gameManager used for updating game state
     * @param sceneManager used for changing scenes
     */
    public ScoreGameController(GameManager gameManager, SceneManager sceneManager) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
        basePathManager = gameManager.getBasePathManager();


    }

    /**
     * Scorekeeper recorded a strike. Update game state and ensure all UI components are correct.
     */
    @FXML
    private void scoreStrike() {
        gameManager.scoreStrike(); // Update game state
        // Update all UI components that may require change due to game state change.
        updateStrikesUI();
        updateBallsUI();
        updateOutsUI();
        updateBasePathUI();
        updateDefenseUI();
        updatePitcherLabel();
        updateInningUI();
        updateBatterLabel();
    }

    /**
     * Scorekeeper recorded a ball. Update game state and ensure all UI components are correct.
     */
    @FXML
    private void scoreBall() {
        gameManager.scoreBall();
        updateBallsUI();
        updateBasePathUI();
        updateScoreUI();
        updatePitcherLabel();
        updateBatterLabel();
    }

    /**
     * When scorekeeper wants to record a pitch, menu will appear to record specific pitch.
     */
    @FXML
    private void showPitchVBox() {
        pitchSelectionVBox.setVisible(true);
    }

    /**
     * Guarantee the outs tracker in UI shows the correct number of outs.
     */
    @FXML
    private void updateOutsUI() {
        // Empty all circles in HBox.
        for (int i = 0; i < 2; i++) {
            Circle circle = (Circle) outsHBox.getChildren().get(i);
            circle.setFill(Color.WHITE);

        }
        // Color as many circles as number of outs.
        for (int i = 0; i < gameManager.getNumOuts(); i++) {
            Circle circle = (Circle) outsHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
    }

    /**
     * Guarantee the strikes tracker shows the correct number of strikes.
     */
    @FXML
    private void updateStrikesUI() {
        // Empty all circles in HBox.
        for (int i = 0; i < 2; i++) {
            Circle circle = (Circle) strikesHBox.getChildren().get(i);
            circle.setFill(Color.WHITE);
        }

        // Color as many circles as number of strikes.
        for (int i = 0; i < gameManager.getStrikeCount(); i++) {
            Circle circle = (Circle) strikesHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
    }

    /**
     * Guarantee the balls tracker shows the correct number of balls.
     */
    private void updateBallsUI() {
        // Empty all circles in HBox.
        for (int i = 0; i < 3; i++) {
            Circle circle = (Circle) ballsHBox.getChildren().get(i);
            circle.setFill(Color.WHITE);
        }

        // Color as many circles as number of balls.
        for (int i = 0; i < gameManager.getBallCount(); i++) {
            Circle circle = (Circle) ballsHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
    }

    /**
     * Guarantee that the UI shows the correct batters on base.
     */
    private void updateBasePathUI() {
        // Check is first base is occupied
        if (basePathManager.getFirstBase() != null) {
            // Highlight first base and show runner name on respective button.
            firstBase.setFill(Color.YELLOW);
            firstBaseRunner.setVisible(true);
            firstBaseRunner.setText(basePathManager.getFirstBase().getPlayerName());

        // Base is unoccupied.
        } else {
            // Make first base blank and hide respective button.
            firstBase.setFill(Color.WHITE);
            firstBaseRunner.setVisible(false);
        }

        // Check if second base is occupied.
        if (basePathManager.getSecondBase() != null) {
            // Highlight second base and show runner name on respective button.
            secondBase.setFill(Color.YELLOW);
            secondBaseRunner.setVisible(true);
            secondBaseRunner.setText(basePathManager.getSecondBase().getPlayerName());

        // Second base is unoccupied
        } else {
            // Make second base blank and hide respective button.
            secondBase.setFill(Color.WHITE);
            secondBaseRunner.setVisible(false);
        }

        // Check if third base is occupied.
        if (basePathManager.getThirdBase() != null) {
            // Highlight third base and show runner name on respective button.
            thirdBase.setFill(Color.YELLOW);
            thirdBaseRunner.setVisible(true);
            thirdBaseRunner.setText(basePathManager.getThirdBase().getPlayerName());

        // Third base is unoccupied.
        } else {
            // Make third base blank and hide respective button.
            thirdBase.setFill(Color.WHITE);
            thirdBaseRunner.setVisible(false);
        }
    }

    /**
     * Guarantee the scoreboard shows accurate scores for each team.
     */
    private void updateScoreUI() {
        // Set score label for each team equal to the number of runs scored.
        awayTeamScoreLabel.setText(gameManager.getAwayTeamRuns());
        homeTeamScoreLabel.setText(gameManager.getHomeTeamRuns());
    }

    /**
     * Guarantee the defense is accurately represented in UI.
     */
    private void updateDefenseUI() {
        // Currently incomplete, currently only handles pitcher
        pitcherButton.setText(gameManager.getCurrentPitcher().getPlayerName());
    }

    /**
     * Guarantee the correct batter is shown in UI.
     */
    private void updateBatterLabel() {
        // Show current name and spot in the batting order.
        batterLabel.setText(gameManager.getCurrentBatterSpot() + ". " + gameManager.getCurrentBatter().getPlayerName());
    }

    /**
     * Guarantee the correct pitcher is shown in the UI.
     */
    private void updatePitcherLabel() {
        // Currently incomplete
        // Update pitcher label with current pitcher.
        pitcherLabel.setText(gameManager.getCurrentPitcher().getPlayerName());

        // Show current pitcher's game stats.
        currentPitcherStatsLabel.setText("P: " + gameManager.getCurrentPitcher().getPitchCount() +
                "\n " + gameManager.getCurrentPitcher().getInningsPitched()
                +  "\nER: 0");
    }

    /**
     * Guarantee that the UI correctly displays what the current inning is.
     */
    private void updateInningUI() {
        if (gameManager.isTopInning) {
            topInningArrow.setVisible(true);
            bottomInningArrow.setVisible(false);
        } else {
            topInningArrow.setVisible(false);
            bottomInningArrow.setVisible(true);
        }

        currentInningLabel.setText(Integer.toString(gameManager.currentInningNumber));
    }
}
