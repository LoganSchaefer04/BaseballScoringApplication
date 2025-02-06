package com.baseballscoringapplication.managers;

import com.baseballscoringapplication.controllers.ScoreGameController;
import com.baseballscoringapplication.controllers.TeamSetController;
import com.baseballscoringapplication.managers.GameManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class SceneManager {
    private Stage stage;
    private Map<String, Class<?>> sceneControllerMap;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void switchScene(String fxmlFile, GameManager gameManager) {
        try {

            String path = "/com/baseballscoringapplication/" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

            if (fxmlFile.equals("team-set.fxml")) {
                loader.setController(new TeamSetController(gameManager, this));
            }
            if (fxmlFile.equals("score-game.fxml")) {
                loader.setController(new ScoreGameController(gameManager, this));
            }




            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
