package com.baseballscoringapplication.controllers;

import com.baseballscoringapplication.managers.GameManager;
import com.baseballscoringapplication.managers.SceneManager;
import com.baseballscoringapplication.gameComponents.Player;
import com.baseballscoringapplication.gameComponents.Team;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
        loadHomeTeamBattingOrder();
        loadAwayTeamBattingOrder();
    }
    @FXML
    public void showBattingOrderSetScreen() {
        sceneManager.switchScene("set-batting-order.fxml", gameManager);
    }

    @FXML
    private void loadTeamDropdowns() {
        Map<String, Team> existingTeamsList = gameManager.getAllTeams();
            homeTeamDropdown.getItems().addAll(existingTeamsList.keySet());
            awayTeamDropdown.getItems().addAll(existingTeamsList.keySet());

    }
    @FXML
    private void loadHomeTeamBattingOrder() {
        int i = 0;
        homeTeamBattingOrder.getChildren().clear();
        for(Player player : gameManager.getHomeTeam().getRoster()) {
            if (i == 9) {
                Line line = new Line(0, 0, 200, 0);
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);
                homeTeamBattingOrder.getChildren().add(line);
            }
            Button button = new Button(player.getPlayerName());
            button.setOnAction(event -> battingPositionSwap(button, homeTeamBattingOrder));
            homeTeamBattingOrder.getChildren().add(button);
            i++;
        }
    }
    @FXML
    private void loadAwayTeamBattingOrder() {
        int i =0;
        awayTeamBattingOrder.getChildren().clear();
        for(Player player : gameManager.getAwayTeam().getRoster()) {
            if (i == 9) {
                Line line = new Line(0, 0, 200, 0);
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);
                awayTeamBattingOrder.getChildren().add(line);
            }
            Button button = new Button(player.getPlayerName());
            button.setOnAction(event -> battingPositionSwap(button, awayTeamBattingOrder));
            awayTeamBattingOrder.getChildren().add(button);
            i++;
        }
    }

    @FXML
    private void setGameHomeTeam() {
        gameManager.setGameHomeTeam(homeTeamDropdown.getValue());
        loadHomeTeamBattingOrder();
    }
    @FXML
    private void setGameAwayTeam() {
        gameManager.setGameAwayTeam(awayTeamDropdown.getValue());
        loadAwayTeamBattingOrder();
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
        sceneManager.switchScene("score-game.fxml", gameManager);
        gameManager.scoreGame();
    }

    private List<String> getButtonTexts(VBox battingOrder) {
        List<String> buttonTexts = new ArrayList<>();

        for(Object node : battingOrder.getChildren()) {
            Button button = (Button) node;
            buttonTexts.add(button.getText());
        }
        return buttonTexts;
    }
}
