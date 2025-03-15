package com.baseballscoringapplication.gameComponents;// Uses a linked list to create a batting order that is easily iterated.

import com.baseballscoringapplication.gameComponents.Player;
import com.baseballscoringapplication.gameComponents.Roster;

import java.util.*;

public class BattingOrder {
    private ArrayList<Player> battingOrder; // Use ArrayList to hold order.
    public int nextBatterUpIndex; // index kept to manage what batter will bat next.

    /**
     * Constructor initializes ArrayList, and creates a generic batting order.
     * Batting order is set to the first 9 players listed in the team's text file.
     *
     * @param teamRoster Roster of team the batting order will be set for.
     *                   Used to retrieve players.
     */
    public BattingOrder(Roster teamRoster) {
        battingOrder = new ArrayList<>();
        genericBattingOrder(teamRoster);
        nextBatterUpIndex = 0;
    }

    /**
     * Method called for customized batting order. Sets the batting order to user specified order.
     *
     * @param newBattingOrder length 9 list containing the names of players that will be in batting order.
     * @param rosterMap - Contains a map of all player names and player objects used to set players in batting order.
     */
    public void setBattingOrder(String[] newBattingOrder, Map<String, Player> rosterMap) {
        for (int i = 0; i < 9; i++) {
            battingOrder.set(i, rosterMap.get(newBattingOrder[i]));
        }
    }

    /**
     * Sets the batting order to the first 9 players created in roster creation.
     * This body of this method will be moved to the constructor if I can't think of any other uses for it.
     *
     * @param teamRoster Roster of team that batting order is being set for.
     */
    private void genericBattingOrder(Roster teamRoster) {
        for (int i = 0; i < 9; i++) {
            battingOrder.add(teamRoster.getRoster().get(i));
        }
    }

    public ArrayList<Player> getBattingOrder() {
        return battingOrder;
    }


    public void addPlayer(Player player) {
        battingOrder.add(player);
    }

    public Player getNextPlayer() {
        return battingOrder.get(nextBatterUpIndex);
    }

    public void queueNextBatter() {
        nextBatterUpIndex = (nextBatterUpIndex + 1) % 9;
    }

    public int getNextBatterUpSpot() {
        if (nextBatterUpIndex == 0) {
            return 1;
        } else {
            return nextBatterUpIndex + 1;
        }
    }
}
