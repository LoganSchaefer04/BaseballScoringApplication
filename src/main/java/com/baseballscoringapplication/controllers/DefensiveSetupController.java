package com.baseballscoringapplication.controllers;

import com.baseballscoringapplication.gameComponents.Player;
import com.baseballscoringapplication.gameComponents.Team;
import com.baseballscoringapplication.managers.GameManager;
import com.baseballscoringapplication.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class DefensiveSetupController {
    private GameManager gameManager;
    private SceneManager sceneManager;
    private TeamSetController teamSetController;
    private Team team;
    @FXML
    private AnchorPane defenseAnchorPane;
    @FXML
    private Button leftField;
    @FXML
    private Button centerField;
    @FXML
    private Button rightField;
    @FXML
    private Button thirdBase;
    @FXML
    private Button shortStop;
    @FXML
    private Button secondBase;
    @FXML
    private Button firstBase;
    @FXML
    private Button catcher;
    @FXML
    private Button designatedHitter;
    @FXML
    private Button submitChangesButton;
    @FXML
    private Pane defensePane;

    public DefensiveSetupController(GameManager gameManager, SceneManager sceneManager,
                                    TeamSetController teamSetController, Team team) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
        this.teamSetController = teamSetController;
        this.team = team;
    }

    public void initialize() {
        // Get batting order, and display the players on the team.
        // TODO: change from batting order to defensive setup to save setup between uses of the scene.
        List<Player> playersList = team.getBattingOrder();
        leftField.setText(playersList.get(0).getPlayerName());
        centerField.setText(playersList.get(1).getPlayerName());
        rightField.setText(playersList.get(2).getPlayerName());
        thirdBase.setText(playersList.get(3).getPlayerName());
        shortStop.setText(playersList.get(4).getPlayerName());
        secondBase.setText(playersList.get(5).getPlayerName());
        firstBase.setText(playersList.get(6).getPlayerName());
        catcher.setText(playersList.get(7).getPlayerName());
        designatedHitter.setText(playersList.get(8).getPlayerName());



    }

    @FXML
    private void submitChanges() {

        String[] buttonTexts = new String[9];
        for (int i = 0; i < 9; i++) {
            Button button = (Button) defensePane.getChildren().get(i);
            buttonTexts[i] = button.getText();
        }
        gameManager.setDefense(team, buttonTexts);
        sceneManager.switchBackToTeamSet(gameManager, teamSetController);
    }

}
