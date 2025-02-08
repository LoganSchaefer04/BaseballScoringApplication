package com.baseballscoringapplication.controllers;

import com.baseballscoringapplication.managers.BasePathManager;
import com.baseballscoringapplication.managers.GameManager;
import com.baseballscoringapplication.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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

    private BasePathManager basePathManager;
    private int outs = 0;

    private GameManager gameManager;
    private SceneManager sceneManager;

    public ScoreGameController(GameManager gameManager, SceneManager sceneManager) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
        basePathManager = gameManager.getBasePathManager();

    }

    @FXML
    private void scoreStrike() {
        outs += gameManager.scoreStrike();
        updateStrikesUI(gameManager.getStrikeCount());
        updateOutsUI();
    }
    @FXML
    private void scoreBall() {
        gameManager.scoreBall();
        updateBallsUI(gameManager.getBallCount());
        updateBasePathUI();
    }
    @FXML
    private void showPitchVBox() {
        pitchSelectionVBox.setVisible(true);
    }
    @FXML
    private void updateOutsUI() {
        for (int i = 0; i < outs; i++) {
            Circle circle = (Circle) outsHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
    }
    @FXML
    private void updateStrikesUI(int strikeCount) {

        for (int i = 0; i < 2; i++) {
            Circle circle = (Circle) strikesHBox.getChildren().get(i);
            circle.setFill(Color.WHITE);

        }
        for (int i = 0; i < strikeCount; i++) {
            Circle circle = (Circle) strikesHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
    }

    private void updateBallsUI(int ballCount) {

        for (int i = 0; i < 3; i++) {
            Circle circle = (Circle) ballsHBox.getChildren().get(i);
            circle.setFill(Color.WHITE);
        }
        for (int i = 0; i < ballCount; i++) {
            Circle circle = (Circle) ballsHBox.getChildren().get(i);
            circle.setFill(Color.RED);
        }
    }

    private void updateBasePathUI() {
        // Check is first base is occupied
        if (basePathManager.getFirstBase() != null) {
            firstBase.setFill(Color.YELLOW);
        } else {
            firstBase.setFill(Color.WHITE);
        }

        // Check if second base is occupied.
        if (basePathManager.getSecondBase() != null) {
            secondBase.setFill(Color.YELLOW);
        } else {
            secondBase.setFill(Color.WHITE);
        }

        // Check if third base is occupied.
        if (basePathManager.getThirdBase() != null) {
            thirdBase.setFill(Color.YELLOW);
        } else {
            thirdBase.setFill(Color.WHITE);
        }
    }
}
