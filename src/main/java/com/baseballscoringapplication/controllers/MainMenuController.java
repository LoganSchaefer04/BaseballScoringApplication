package com.baseballscoringapplication.controllers;

import com.baseballscoringapplication.managers.GameManager;
import com.baseballscoringapplication.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    private GameManager gameManager;
    private SceneManager sceneManager;

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void setGameManager(GameManager gameManager) {
        this.gameManager= gameManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    @FXML
    public void showTeamSetScreen() {
        if (gameManager == null) {
            System.out.println("gameManager is null");
        } else {
            System.out.println("gameManager is NOT null");
        }
        sceneManager.switchScene("team-set.fxml", gameManager);

    }



}



