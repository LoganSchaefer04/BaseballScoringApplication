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

    public void addPitch(Pitch pitch) {

    }

    public void addOutsRecorded(int outsRecorded) {

    }

    public float getInningsPitched() {
        return 0;
    }

    public int getPitchCount() {
        System.out.println("Called method in Player");
        return 0;
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


