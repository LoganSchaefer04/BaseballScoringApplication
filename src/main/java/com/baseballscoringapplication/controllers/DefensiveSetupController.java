package com.baseballscoringapplication.controllers;

import com.baseballscoringapplication.gameComponents.Player;
import com.baseballscoringapplication.gameComponents.Team;
import com.baseballscoringapplication.managers.GameManager;
import com.baseballscoringapplication.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

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

    public DefensiveSetupController(GameManager gameManager, SceneManager sceneManager,
                                    TeamSetController teamSetController, Team team) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
        this.teamSetController = teamSetController;
        this.team = team;
    }

    public void initialize() {
        /*
        In order to make this class work where there is a graphic for setting the defensive lineup,
        TeamSetController is going to have to update the gameManager every time a switch to the batting order is made.
        This is because DefensiveSetupController needs to access the batting orders through GameManager every time the
        scene needs to be opened.
         */
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
        sceneManager.switchBackToTeamSet(gameManager, teamSetController);
    }

}
