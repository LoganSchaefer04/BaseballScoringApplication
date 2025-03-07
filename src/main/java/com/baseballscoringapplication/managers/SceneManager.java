package com.baseballscoringapplication.managers;

import com.baseballscoringapplication.controllers.DefensiveSetupController;
import com.baseballscoringapplication.controllers.ScoreGameController;
import com.baseballscoringapplication.controllers.TeamSetController;
import com.baseballscoringapplication.gameComponents.Team;
import com.baseballscoringapplication.managers.GameManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class SceneManager {
    private Stage stage;
    private Map<String, Class<?>> sceneControllerMap;

    /**
     * Creates instance of SceneManager for switching between scenes.
     * @param stage
     */
    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    /**
     * Switches current scene.
     * @param fxmlFile file for which scene to switch to.
     * @param gameManager instance of gameManager to inject into controller classes.
     */
    public void switchScene(String fxmlFile, GameManager gameManager) {
        try {
            // Create path to fxmlF file.
            // Create FXMLLoader to prepare for loading scene.
            String path = "/com/baseballscoringapplication/" + fxmlFile;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));


            /* Create instance of controller class for next scene and inject gameManager and SceneManager.
            * This is now how I would like this to work. I had lots of trouble getting the front-end working,
            * so this is going to work for now. If I had to switch to another scene and then back, this would
            * break the program, but it will work for now.
            */
            if (fxmlFile.equals("score-game.fxml")) {
                loader.setController(new ScoreGameController(gameManager, this));
            }



            // Load scene and show.
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToTeamSet(GameManager gameManager) {
        String path = "/com/baseballscoringapplication/team-set.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

        TeamSetController teamSetController = new TeamSetController(gameManager, this);
        loader.setController(teamSetController);


        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            teamSetController.injectScene(scene);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToDefensiveSetup(GameManager gameManager, TeamSetController teamSetController, Team team,
                                       Scene teamSetScene) {

        try {
            String path = "/com/baseballscoringapplication/defensive-setup.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

            loader.setController(new DefensiveSetupController(gameManager, this, teamSetController, team,
                    teamSetScene));


            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Switch back to team-set.fxml. Scene has been saved.
     *
     * @param teamSetScene the exact same scene to switch back to.
     */
    public void switchBackToTeamSet(Scene teamSetScene) {
        stage.setScene(teamSetScene);
        stage.show();

        /**
         * Just realized that I can save the scene as an attribute of SceneManager instead of passing it around as
         * a parameter into multiple different methods
         * TODO: Fix this^^^^
         */

    }

}
