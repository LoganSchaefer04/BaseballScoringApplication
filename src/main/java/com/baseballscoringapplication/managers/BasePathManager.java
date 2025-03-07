package com.baseballscoringapplication.managers;

import com.baseballscoringapplication.gameComponents.Player;

public class BasePathManager {
    protected Player[] basePaths = new Player[3];
    private GameManager gameManager;

    public BasePathManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Move all runners forward one base.
     * Put batter on first base.
     *
     * @param batter batter to record single to.
     * @return the number of runs scored on the play.
     */
    public int scoreSingle(Player batter) {
        int runsScored = 0;
        for (Player runner : basePaths) {

        }
        return runsScored;
    }

    /**
     * Move all runners forward on the bases.
     * Put batter on first base.
     *
     * @param player Player to record walk for.
     *               Put Player on first base.
     */
    public void scoreWalk(Player player) {
        for (int i = 2; i >= 0; i--) {
            if (basePaths[i] != null) {
                switch (i) {
                    case 2:
                        gameManager.scoreRun(player);
                        break;
                    case 1:
                        basePaths[2] = basePaths[1];
                        basePaths[1] = null;
                        break;
                    case 0:
                        basePaths[1] = basePaths[0];
                        basePaths[0] = null;
                }
            }
        }
        basePaths[0] = player;
    }

    /**
     * Remove all players from bases.
     */
    public void clearBases() {
        for (int i = 0; i < 3; i++) {
            basePaths[i] = null;
        }
    }

    public Player getFirstBase() {
        return basePaths[0];
    }

    public Player getSecondBase() {
        return basePaths[1];
    }

    public Player getThirdBase() {
        return basePaths[2];
    }
}

