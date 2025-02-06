package com.baseballscoringapplication.gameComponents;

public class Player {
    String playerName;
    String position;
    public Player(String name, String playerPosition) {
        playerName = name;
        position = playerPosition;
    }
    public Player (String name) {
        playerName = name;
    }
    public Player() {
        playerName = "John Doe";
        position = "Undefined";
    }


    public void setPosition(String position) {
        this.position = position;
    }
    public String getPlayerPosition() {
        return position;
    }

    public String getPlayerName() {
        return playerName;
    }
}


