package com.baseballscoringapplication.gameComponents;

import com.baseballscoringapplication.GameStats;

public class Player {

    String playerName;
    String position;
    GameStats gameStats;

    /**
     * Create new player. Set name and position.
     *
     * @param name Name of new player.
     * @param playerPosition - Position of new player.
     */
    public Player(String name, String playerPosition) {
        playerName = name;
        position = playerPosition;
        gameStats = new GameStats();
    }
    public Player (String name) {
        playerName = name;
    }
    public Player() {
        playerName = "John Doe";
        position = "Undefined";
    }

    public void addPitch(Pitch pitch) {

    }

    public void addOutsRecorded(int outsRecorded) {

    }

    public float getInningsPitched() {
        return 0;
    }

    public int getPitchCount() {
        return 0;
    }

    public String getPlayerPosition() {
        return position;
    }

    public String getPlayerName() {
        return playerName;
    }

    public GameStats getGameStats() {
        return gameStats;
    }




}


