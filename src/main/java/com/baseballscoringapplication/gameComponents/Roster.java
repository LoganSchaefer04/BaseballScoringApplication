package com.baseballscoringapplication.gameComponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Roster {

    private static final int DEFAULT_ROSTER_SIZE = 26;
    private ArrayList<Player> roster;
    private ArrayList<Player> availablePitchers;
    private Map<String, Player> rosterMap;


    /**
     * Constructor for already existing team.
     *
     * @param file Text file that contains all players and their positions.
     */
    public Roster(File file) {
        // Initialize data structures for maintaining roster.
        roster = new ArrayList<>(DEFAULT_ROSTER_SIZE);
        rosterMap = new HashMap<>();
        // Create scanner object to read text file.
        try {
            Scanner teamInput = new Scanner(file);
            String currentPlayerName;
            String currentPlayerPosition;

            // Iterate over all players in the 26-man roster.
            for (int i = 0; i < 26; i++) {
                // Check if Scanner object has another player to read.
                if (teamInput.hasNext()) {
                    // Record next player name and position.
                    currentPlayerName = teamInput.nextLine();
                    currentPlayerPosition = teamInput.nextLine();

                // No more players to read. Break loop.
                } else {
                    break;
                }

                // Check if new player is a pitcher.
                if (currentPlayerPosition.equals("P")) {
                    // Create a new instance of pitcher and add to roster.
                    Player player = new Pitcher(currentPlayerName, currentPlayerPosition);
                    roster.add(player);
                    rosterMap.put(currentPlayerName, player);

                // Player is a position player.
                } else {
                    // Create player and add to roster.
                    // I imagine there will be a class in the future for position players.
                    Player player = new Player(currentPlayerName, currentPlayerPosition);
                    roster.add(player);
                    rosterMap.put(currentPlayerName, player);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
    }

        public ArrayList<Player> getRoster(){
            return roster;
        }
        public Map<String, Player> getRosterMap() {
            return rosterMap;
        }

        public Player getPlayer(int i) {
            return roster.get(i);
        }
        public Player getPlayer(String playerName) {
            //System.out.println("Returning " + rosterMap.get(playerName).getPlayerName());
            return rosterMap.get(playerName);
        }

        public ArrayList<Player> getAvailablePitchers() {
            return availablePitchers;
        }

        public int size() {
            return roster.size();
        }

        public void printRoster() {
            for (Player player : roster) {
                System.out.println(player.getPlayerName());
            }
        }
    }



