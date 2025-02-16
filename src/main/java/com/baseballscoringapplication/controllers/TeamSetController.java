package com.baseballscoringapplication.controllers;

import com.baseballscoringapplication.gameComponents.Pitcher;
import com.baseballscoringapplication.managers.GameManager;
import com.baseballscoringapplication.managers.SceneManager;
import com.baseballscoringapplication.gameComponents.Player;
import com.baseballscoringapplication.gameComponents.Team;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class TeamSetController {

    private GameManager gameManager;
    private SceneManager sceneManager;
    @FXML
    private ComboBox<String> awayTeamDropdown;
    @FXML
    private ComboBox<String> homeTeamDropdown;
    @FXML
    private VBox homeTeamBattingOrder;
    @FXML
    private VBox awayTeamBattingOrder;
    @FXML
    private VBox homeTeamPitchers;
    @FXML
    private VBox awayTeamPitchers;
    @FXML
    private Label homeTeamStartingPitcher;
    @FXML
    private Label awayTeamStartingPitcher;
    private Button firstSelected = null;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public TeamSetController(GameManager gameManager, SceneManager sceneManager) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        loadTeamDropdowns();
        loadHomeTeam();
        loadAwayTeam();
    }

    @FXML
    private void loadTeamDropdowns() {
        Map<String, Team> existingTeamsList = gameManager.getAllTeams();
            homeTeamDropdown.getItems().addAll(existingTeamsList.keySet());
            awayTeamDropdown.getItems().addAll(existingTeamsList.keySet());

    }
    @FXML
    private void loadHomeTeam() {
        int i = 0;
        homeTeamBattingOrder.getChildren().clear();
        homeTeamPitchers.getChildren().clear();
        for(Player player : gameManager.getHomeTeam().getRoster()) {
            if (i == 9) {
                Line line = new Line(0, 0, 200, 0);
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);
                homeTeamBattingOrder.getChildren().add(line);
            }
            Button button = new Button(player.getPlayerName());
            if (player instanceof Pitcher) {
                button.setOnAction(event -> startingPitcherSwap(button));
                homeTeamPitchers.getChildren().add(button);
                homeTeamStartingPitcher.setText(button.getText());
            } else {
                button.setOnAction(event -> battingPositionSwap(button, homeTeamBattingOrder));
                homeTeamBattingOrder.getChildren().add(button);
                i++;
            }
        }
    }

    @FXML
    private void loadAwayTeam() {
        int i =0;
        awayTeamBattingOrder.getChildren().clear();
        awayTeamPitchers.getChildren().clear();
        for(Player player : gameManager.getAwayTeam().getRoster()) {
            if (i == 9) {
                Line line = new Line(0, 0, 200, 0);
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);
                awayTeamBattingOrder.getChildren().add(line);
            }
            Button button = new Button(player.getPlayerName());
            if (player instanceof Pitcher) {

                button.setOnAction(event -> startingPitcherSwap(button));
                awayTeamPitchers.getChildren().add(button);
                awayTeamStartingPitcher.setText(button.getText());
                button.setOnAction(event -> battingPositionSwap(button, awayTeamBattingOrder));
                awayTeamBattingOrder.getChildren().add(button);
                i++;
            } else {
                button.setOnAction(event -> battingPositionSwap(button, awayTeamBattingOrder));
                awayTeamBattingOrder.getChildren().add(button);
                i++;
            }
        }
    }

    private void startingPitcherSwap(Button clickedButton) {
        if (homeTeamPitchers.getChildren().contains(clickedButton)) {
            homeTeamStartingPitcher.setText(clickedButton.getText());
        } else {
            awayTeamStartingPitcher.setText(clickedButton.getText());
        }
    }

    @FXML
    private void setGameHomeTeam() {
        gameManager.setGameHomeTeam(homeTeamDropdown.getValue());
        loadHomeTeam();
    }
    @FXML
    private void setGameAwayTeam() {
        gameManager.setGameAwayTeam(awayTeamDropdown.getValue());
        loadAwayTeam();
    }
@FXML
    private void battingPositionSwap(Button clickedButton, VBox battingOrder) {
        if (firstSelected == null) {
            firstSelected = clickedButton;
            firstSelected.setStyle("-fx-background-color: lightblue");
        } else if (firstSelected == clickedButton) {
            firstSelected.setStyle("");
            firstSelected = null;
        } else {
                System.out.println("Removing");
                int index1 = battingOrder.getChildren().indexOf(firstSelected);
                int index2 = battingOrder.getChildren().indexOf(clickedButton);

                battingOrder.getChildren().remove(firstSelected);
                battingOrder.getChildren().remove(clickedButton);

                try {
                    battingOrder.getChildren().add(index1, clickedButton);
                    battingOrder.getChildren().add(index2, firstSelected);
                } catch (IndexOutOfBoundsException e) {
                    battingOrder.getChildren().add(firstSelected);
                    battingOrder.getChildren().add(clickedButton);
                }

                firstSelected.setStyle("");
                firstSelected = null;
        }
    }

    @FXML
    private void startGame() {
        if (homeTeamBattingOrder.getChildren().size() >= 10) {
            homeTeamBattingOrder.getChildren().remove(9);
        }
        if (awayTeamBattingOrder.getChildren().size() >= 10) {
            awayTeamBattingOrder.getChildren().remove(9);
        }
        gameManager.setHomeBattingOrder(getButtonTexts(homeTeamBattingOrder));
        gameManager.setAwayBattingOrder(getButtonTexts(awayTeamBattingOrder));
        gameManager.setStartingPitchers(homeTeamStartingPitcher.getText(), awayTeamStartingPitcher.getText());
        gameManager.scoreGame();
        sceneManager.switchScene("score-game.fxml", gameManager);
    }

    private List<String> getButtonTexts(VBox battingOrder) {
        List<String> buttonTexts = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            Button button = (Button) battingOrder.getChildren().get(i);
            buttonTexts.add(button.getText());
        }
        /*
        for(Object node : battingOrder.getChildren()) {
            Button button = (Button) node;
            buttonTexts.add(button.getText());
        }
         */
        return buttonTexts;
    }
}
