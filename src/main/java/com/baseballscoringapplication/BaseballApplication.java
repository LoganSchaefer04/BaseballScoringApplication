package com.baseballscoringapplication;

import com.baseballscoringapplication.controllers.MainMenuController;
import com.baseballscoringapplication.managers.GameManager;
import com.baseballscoringapplication.managers.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class BaseballApplication extends Application {

    private static GameRepository gameRepository = new GameRepository();

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Logan's Baseball Scoring Application");
        SceneManager sceneManager = new SceneManager(stage);
        GameManager gameManager = new GameManager();
        gameManager.createGame();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-menu.fxml"));
        Parent root = loader.load();

        MainMenuController controller = loader.getController();
        controller.setSceneManager(sceneManager);
        controller.setGameManager(gameManager);



        Scene scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}