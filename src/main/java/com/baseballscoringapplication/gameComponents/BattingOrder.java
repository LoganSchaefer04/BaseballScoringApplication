package com.baseballscoringapplication.gameComponents;// Uses a linked list to create a batting order that is easily iterated.

import com.baseballscoringapplication.gameComponents.Player;
import com.baseballscoringapplication.gameComponents.Roster;

import java.util.*;

public class BattingOrder {
    private ArrayList<Player> battingOrder;
    private int nextBatterUpIndex;

    public BattingOrder(Roster teamRoster) {
        battingOrder = new ArrayList<>();
        genericBattingOrder(teamRoster);
        nextBatterUpIndex = 0;
        }
    /*
    public void selectBattingOrder(Roster teamRoster) {
        int choice = 0;
        Scanner inputChoice = new Scanner(System.in);
        System.out.println("Would you like to manually input a batting order?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        choice = inputChoice.nextInt();

        switch (choice) {
            case 0:
                System.out.println("Invalid");
                break;
            case 1:
                manualBattingOrderInput(teamRoster);
            case 2:
                System.out.println(battingOrder.size());
        }
    }

     */
    /*private void manualBattingOrderInput(Roster teamRoster) {
        System.out.println("Please select your batting order in order");
        for (int i = 0; i < teamRoster.size(); i++) {
            System.out.print((i + 1) + ") " + teamRoster.getPlayer(i).getPlayerName());
            System.out.println(" " + teamRoster.getPlayer(i).getPlayerPosition());
        }
        Scanner battingOrderInput = new Scanner(System.in);
        int selection = 0;
        for (int i = 0; i < 9; i++) {
            selection = battingOrderInput.nextInt();
            addPlayer(teamRoster.getPlayer(selection - 1));
        }


    }

     */

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
