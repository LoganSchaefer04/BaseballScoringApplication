package com.baseballscoringapplication.controllers;

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

    public DefensiveSetupController(GameManager gameManager, SceneManager sceneManager,
                                    TeamSetController teamSetController) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
        this.teamSetController = teamSetController;
    }

    public void initialize() {
        /*
        In order to make this class work where there is a graphic for setting the defensive lineup,
        TeamSetController is going to have to update the gameManager every time a switch to the batting order is made.
        This is because DefensiveSetupController needs to access the batting orders through GameManager every time the
        scene needs to be opened.
         */
    }

}
