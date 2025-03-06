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
    private ComboBox<String> awayTeamDropdown; // Dropdown for away team selection.
    @FXML
    private ComboBox<String> homeTeamDropdown; // Dropdown for home team selection.
    @FXML
    private VBox homeTeamBattingOrder; // VBox containing home team position players.
    @FXML
    private VBox awayTeamBattingOrder; // VBox containing away team position players.
    @FXML
    private VBox homeTeamPitchers; // VBox containing home team pitchers.
    @FXML
    private VBox awayTeamPitchers; // VBox containing away team pitchers.
    @FXML
    private Label homeTeamStartingPitcher; // Label listing home team starting pitcher.
    @FXML
    private Label awayTeamStartingPitcher; // Label listing away team starting pitcher.
    @FXML
    private Button setAwayDefenseButton;
    @FXML
    private Button setHomeDefenseButton;
    private Button firstSelected = null; // Button used for batting order manipulation.

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Allows access to gameManager and sceneManager methods.
     *
     * @param gameManager allows controller to update the game state
     * @param sceneManager allows controller to switch scene.
     */
    public TeamSetController(GameManager gameManager, SceneManager sceneManager) {
        this.gameManager = gameManager;
        this.sceneManager = sceneManager;
    }

    /**
     * Initialize is called when FXMLLoader calls load() in SceneManager.
     */
    @FXML
    public void initialize() {
        // Load team selection dropdowns.
        Map<String, Team> existingTeamsList = gameManager.getAllTeams();
        homeTeamDropdown.getItems().addAll(existingTeamsList.keySet());
        awayTeamDropdown.getItems().addAll(existingTeamsList.keySet());

        // Load VBoxes for both teams.
        loadHomeTeam();
        loadAwayTeam();
        gameManager.setHomeBattingOrder(getButtonTexts(homeTeamBattingOrder));
        gameManager.setAwayBattingOrder(getButtonTexts(awayTeamBattingOrder));
    }

    /**
     * Loads home team players into the correct VBox based on position.
     */
    @FXML
    private void loadHomeTeam() {
        // Index to place red line at index 9.
        int i = 0;

        // Clear previous contents in batting order and pitchers' VBox.
        homeTeamBattingOrder.getChildren().clear();
        homeTeamPitchers.getChildren().clear();

        // Loop through all players in roster and place into respective VBox.
        for(Player player : gameManager.getHomeTeam().getRoster()) {
            if (i == 9) {
                // Insert red line at index 9 to separate starters and bench players.
                Line line = new Line(0, 0, 200, 0);
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);
                homeTeamBattingOrder.getChildren().add(line);
            }

            // Create new button for new player.
            Button button = new Button(player.getPlayerName());

            // Check if player is a pitcher or position player.
            if (player instanceof Pitcher) {
                // Player is pitcher. Add to pitchers Vbox.
                button.setOnAction(event -> startingPitcherSwap(button));
                homeTeamPitchers.getChildren().add(button);
                homeTeamStartingPitcher.setText(button.getText());
            } else {
                // Player is position player. Add to batting order VBox.
                button.setOnAction(event -> battingPositionSwap(button, homeTeamBattingOrder));
                homeTeamBattingOrder.getChildren().add(button);
                i++;
            }
        }
    }

    /**
     *  Loads away team players into the correct VBox based on position.
     */
    @FXML
    private void loadAwayTeam() {
        // Index to place red line at position 9.
        int i = 0;

        // Clear previous contents in batting order and pitcher's VBox.
        awayTeamBattingOrder.getChildren().clear();
        awayTeamPitchers.getChildren().clear();

        // Loop through all players in roster and place in respective VBox.
        for(Player player : gameManager.getAwayTeam().getRoster()) {
            if (i == 9) {
                Line line = new Line(0, 0, 200, 0);
                line.setStroke(Color.RED);
                line.setStrokeWidth(2);
                awayTeamBattingOrder.getChildren().add(line);
            }

            // Create new button for new player.
            Button button = new Button(player.getPlayerName());

            // Check if player is a pitcher.
            if (player instanceof Pitcher) {
                // Set button action for starting pitcher swap.
                // Add button to pitchers' VBox.
                button.setOnAction(event -> startingPitcherSwap(button));
                awayTeamPitchers.getChildren().add(button);
                awayTeamStartingPitcher.setText(button.getText());

            // Otherwise, the player is a position player.
            } else {
                // Set button action for batting order manipulation.
                // Add button to batting order VBox.
                button.setOnAction(event -> battingPositionSwap(button, awayTeamBattingOrder));
                awayTeamBattingOrder.getChildren().add(button);
                i++;
            }
        }
    }

    /**
     * Set starting pitcher if pitcher button is selected.
     *
     * @param clickedButton Holds the name of the pitcher who will become starter.
     */
    private void startingPitcherSwap(Button clickedButton) {
        // Check if clicked button corresponds to home team VBox.
        if (homeTeamPitchers.getChildren().contains(clickedButton)) {
            // Set home starting pitcher to corresponding button.
            homeTeamStartingPitcher.setText(clickedButton.getText());

        // Otherwise update away team starting pitcher.
        } else {
            // Set away starting pitcher to corresponding button.
            awayTeamStartingPitcher.setText(clickedButton.getText());
        }
    }

    /**
     * Set home team if new team is chosen from dropdown menu.
     */
    @FXML
    private void setGameHomeTeam() {
        // Update home team in GameManager.
        gameManager.setGameHomeTeam(homeTeamDropdown.getValue());
        // Update home team VBoxes.
        loadHomeTeam();
        gameManager.setHomeBattingOrder(getButtonTexts(homeTeamBattingOrder));
    }

    /**
     * Set away team if new team is chosen from dropdown menu.
     */
    @FXML
    private void setGameAwayTeam() {
        // Update away team in GameManager.
        gameManager.setGameAwayTeam(awayTeamDropdown.getValue());
        // Update the away team VBoxes.
        loadAwayTeam();
        gameManager.setAwayBattingOrder(getButtonTexts(awayTeamBattingOrder));
    }

    /**
     * Swaps buttons in batting order VBox.
     *
     * @param clickedButton Button that has just been clicked.
     * @param battingOrder VBox where the swap will take place.
     */
    @FXML
    private void battingPositionSwap(Button clickedButton, VBox battingOrder) {
        // Check if clickedButton is the first selection in a swap.
        if (firstSelected == null) {
            // Queue clickedButton into swap and highlight it.
            firstSelected = clickedButton;
            firstSelected.setStyle("-fx-background-color: lightblue");

        // Check if same button is clicked twice.
        } else if (firstSelected == clickedButton) {
            // Dequeue clickedButton from swap and undo highlighting.
            firstSelected.setStyle("");
            firstSelected = null;

        // Otherwise, a swap must occur.
        } else {
                // Record indexes for buttons that need to be swapped.
                int index1 = battingOrder.getChildren().indexOf(firstSelected);
                int index2 = battingOrder.getChildren().indexOf(clickedButton);

                // Remove both buttons from VBox.
                battingOrder.getChildren().remove(firstSelected);
                battingOrder.getChildren().remove(clickedButton);

                // Try swapping buttons
                try {
                    battingOrder.getChildren().add(index1, clickedButton);
                    battingOrder.getChildren().add(index2, firstSelected);

                // The last two indexes were chosen
                } catch (IndexOutOfBoundsException e) {
                    // Add buttons to end.
                    battingOrder.getChildren().add(firstSelected);
                    battingOrder.getChildren().add(clickedButton);
                }

                // Reset firstSelected to prepare for new swap.
                firstSelected.setStyle("");
                firstSelected = null;
        }
        // GameManager needs these updates in order to display correct players when setting defense.
        gameManager.setHomeBattingOrder(getButtonTexts(homeTeamBattingOrder));
        gameManager.setAwayBattingOrder(getButtonTexts(awayTeamBattingOrder));

    }

    /**
     * Sets all required information for baseball game to begin.
     */
    @FXML
    private void startGame() {
        // Remove red line from VBox if it exists.
        if (homeTeamBattingOrder.getChildren().size() >= 10) {
            homeTeamBattingOrder.getChildren().remove(9);
        }
        if (awayTeamBattingOrder.getChildren().size() >= 10) {
            awayTeamBattingOrder.getChildren().remove(9);
        }

        // Set batting orders based on given order in VBox list.
        gameManager.setHomeBattingOrder(getButtonTexts(homeTeamBattingOrder));
        gameManager.setAwayBattingOrder(getButtonTexts(awayTeamBattingOrder));

        // Set starting pitchers to pitchers set in UI.
        gameManager.setStartingPitchers(homeTeamStartingPitcher.getText(), awayTeamStartingPitcher.getText());

        // Tell GameManager to create the first inning.
        gameManager.scoreGame();

        // Switch to scoreGame scene.
        sceneManager.switchScene("score-game.fxml", gameManager);
    }

    /**
     * Return a list of players that will be included in the batting order.
     *
     * @param battingOrder contains batting order in first 9 buttons.
     * @return List of 9 players in starting batting order.
     */
    public List<String> getButtonTexts(VBox battingOrder) {
        // Create list of strings to return.
        List<String> buttonTexts = new ArrayList<>();

        // For the first 9 buttons, add their names to buttonTexts.
        for (int i = 0; i < 9; i++) {
            Button button = (Button) battingOrder.getChildren().get(i);
            buttonTexts.add(button.getText());
        }
        // Return String list of players.
        return buttonTexts;
    }


    @FXML
    private void setAwayDefense() {
        sceneManager.switchToDefensiveSetup(gameManager, this, gameManager.getAwayTeam());
    }

    @FXML
    private void setHomeDefense() {
        sceneManager.switchToDefensiveSetup(gameManager, this, gameManager.getHomeTeam());
    }
}
