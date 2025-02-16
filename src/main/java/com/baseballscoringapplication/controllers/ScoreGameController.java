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
    private VBox pitchSelectionVBox;
    @FXML
    private HBox outsHBox;
    @FXML
    private HBox ballsHBox;
    @FXML
    private HBox strikesHBox;
    @FXML
    private Rectangle firstBase;
    @FXML
    private Rectangle secondBase;
    @FXML
    private Rectangle thirdBase;
    @FXML
    private Button firstBaseRunner;
    @FXML
    private Button secondBaseRunner;
    @FXML
    private Button thirdBaseRunner;
    @FXML
    private Label awayTeamNameLabel;
    @FXML
    private Label homeTeamNameLabel;
    @FXML
    private Label awayTeamScoreLabel;
    @FXML
    private Label homeTeamScoreLabel;
    @FXML
    private Button pitcherButton;
    @FXML
    private Label pitcherLabel;
    @FXML
    private Label currentPitcherStatsLabel;
    @FXML
    private Label batterLabel;
    @FXML
    private Label currentBatterStatsLabel;
    @FXML
    private Polygon topInningArrow;
    @FXML
    private Polygon bottomInningArrow;
    @FXML
    private Label currentInningLabel;

    private BasePathManager basePathManager;

    private GameManager gameManager;
    private SceneManager sceneManager;

    public void initialize() {
        awayTeamNameLabel.setText(gameManager.getAwayTeam().getTeamName());
        homeTeamNameLabel.setText(gameManager.getHomeTeam().getTeamName());
        awayTeamScoreLabel.setText(gameManager.getAwayTeamRuns());
        homeTeamScoreLabel.setText(gameManager.getHomeTeamRuns());
        pitcherButton.setText(gameManager.getCurrentPitcher().getPlayerName());
        pitcherLabel.setText("P: " + gameManager.getCurrentPitcher().getPlayerName());
        currentPitcherStatsLabel.setText("P: 0\nIP: 0\nER: 0");
        batterLabel.setText("1. " + gameManager.getCurrentBatter().getPlayerName());
        currentBatterStatsLabel.setText("0-0");
        bottomInningArrow.setVisible(false);
        currentInningLabel.setTextAlignment(TextAlignment.CENTER);
        currentInningLabel.setText("1");

    }

    public ScoreGameController(GameManager gameManager, SceneManager sceneManager) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
        basePathManager = gameManager.getBasePathManager();


    }

    @FXML
    private void scoreStrike() {
        gameManager.scoreStrike();
        updateStrikesUI();
        updateBallsUI();
        updateOutsUI();
        updateBasePathUI();
        updateDefenseUI();
        updatePitcherLabel();
        updateInningUI();
    }
    @FXML
    private void scoreBall() {
        gameManager.scoreBall();
        updateBallsUI();
        updateBasePathUI();
        updateScoreUI();
        updatePitcherLabel();
    }
    @FXML
    private void showPitchVBox() {
        pitchSelectionVBox.setVisible(true);
    }
    @FXML
    private void updateOutsUI() {
        for (int i = 0; i < 2; i++) {
            Circle circle = (Circle) outsHBox.getChildren().get(i);
            circle.setFill(Color.WHITE);

        }

        for (int i = 0; i < gameManager.getNumOuts(); i++) {
            Circle circle = (Circle) outsHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
    }
    @FXML
    private void updateStrikesUI() {

        for (int i = 0; i < 2; i++) {
            Circle circle = (Circle) strikesHBox.getChildren().get(i);
            circle.setFill(Color.WHITE);

        }
        for (int i = 0; i < gameManager.getStrikeCount(); i++) {
            Circle circle = (Circle) strikesHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
        updateBatterLabel();
    }

    private void updateBallsUI() {

        for (int i = 0; i < 3; i++) {
            Circle circle = (Circle) ballsHBox.getChildren().get(i);
            circle.setFill(Color.WHITE);
        }
        for (int i = 0; i < gameManager.getBallCount(); i++) {
            Circle circle = (Circle) ballsHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
        updateBatterLabel();
    }

    private void updateBasePathUI() {
        // Check is first base is occupied
        if (basePathManager.getFirstBase() != null) {
            firstBase.setFill(Color.YELLOW);
            firstBaseRunner.setVisible(true);
            firstBaseRunner.setText(basePathManager.getFirstBase().getPlayerName());
        } else {
            firstBase.setFill(Color.WHITE);
            firstBaseRunner.setVisible(false);
        }

        // Check if second base is occupied.
        if (basePathManager.getSecondBase() != null) {
            secondBase.setFill(Color.YELLOW);
            secondBaseRunner.setVisible(true);
            secondBaseRunner.setText(basePathManager.getSecondBase().getPlayerName());
        } else {
            secondBase.setFill(Color.WHITE);
            secondBaseRunner.setVisible(false);
        }

        // Check if third base is occupied.
        if (basePathManager.getThirdBase() != null) {
            thirdBase.setFill(Color.YELLOW);
            thirdBaseRunner.setVisible(true);
            thirdBaseRunner.setText(basePathManager.getThirdBase().getPlayerName());
        } else {
            thirdBase.setFill(Color.WHITE);
            thirdBaseRunner.setVisible(false);
        }
    }

    private void updateScoreUI() {
        awayTeamScoreLabel.setText(gameManager.getAwayTeamRuns());
        homeTeamScoreLabel.setText(gameManager.getHomeTeamRuns());
    }

    private void updateDefenseUI() {
        // Currently incomplete, currently only handles pitcher
        pitcherButton.setText(gameManager.getCurrentPitcher().getPlayerName());
    }

    private void updateBatterLabel() {
        batterLabel.setText(gameManager.getCurrentBatterSpot() + ". " + gameManager.getCurrentBatter().getPlayerName());
    }

    private void updatePitcherLabel() {
        pitcherLabel.setText(gameManager.getCurrentPitcher().getPlayerName());
        currentPitcherStatsLabel.setText("P: " + gameManager.getCurrentPitcher().getPitchCount() +
                "\n " + gameManager.getCurrentPitcher().getInningsPitched() + "\nER: 0");
    }

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
