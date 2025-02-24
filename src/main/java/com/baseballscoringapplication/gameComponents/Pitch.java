package com.baseballscoringapplication.gameComponents;

public class Pitch {

    private Boolean isStrike;

    public Pitch(String pitch, int strikeCount) {
        processStrike(pitch, strikeCount);
    }

    /**
     * Returns if a specific pitch was a strike.
     * This method exists for when stats queries are being made.
     *
     * @return if a pitch is a strike or not.
     */
    public boolean isStrike() {
        return isStrike;
    }

    /**
     * Set pitch to be a ball or strike.
     * Process if a foul ball counts a strike or not.
     *
     * @param pitch String of pitch result.
     * @param strikeCount Used to process if a foul ball counts as a strike.
     */
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
