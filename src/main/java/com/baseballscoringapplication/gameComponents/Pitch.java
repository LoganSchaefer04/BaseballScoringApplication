package com.baseballscoringapplication.gameComponents;

public class Pitch {

    Boolean isStrike;

    public Pitch(String pitch, int strikeCount) {
        processStrike(pitch, strikeCount);
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void processStrike(String pitch, int strikeCount) {
        switch(pitch) {
            case "Strike":
                isStrike = true;
                break;
            case "Ball":
                isStrike = false;
                break;
            case "Foul Ball":
                isStrike = strikeCount < 2;
                break;
        }
    }
}
