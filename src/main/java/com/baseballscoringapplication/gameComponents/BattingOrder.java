package com.baseballscoringapplication.gameComponents;// Uses a linked list to create a batting order that is easily iterated.

import com.baseballscoringapplication.gameComponents.Player;
import com.baseballscoringapplication.gameComponents.Roster;

import java.util.*;

public class BattingOrder {
    private ArrayList<Player> battingOrder;
    public int nextBatterUpIndex;

    public BattingOrder(Roster teamRoster) {
        battingOrder = new ArrayList<>();
        genericBattingOrder(teamRoster);
        nextBatterUpIndex = 0;
        }

    public void setBattingOrder(List<String> newBattingOrder, Map<String, Player> rosterMap) {
        for (int i = 0; i < 9; i++) {
            battingOrder.set(i, rosterMap.get(newBattingOrder.get(i)));
        }
    }

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
}
