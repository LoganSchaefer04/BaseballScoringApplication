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

    public Roster() {
        String[] defensivePositions = {"P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF"};
        roster = new ArrayList<>(DEFAULT_ROSTER_SIZE);
        availablePitchers = new ArrayList<>(DEFAULT_ROSTER_SIZE);
        rosterMap = new HashMap<>();
        Player player = new Player("John Doe", "P");
        roster.add(player);
        availablePitchers.add(player);

        for (int i = 1; i < 9; i++) {
            Player newPlayer = new Player("John Doe", defensivePositions[i]);
            roster.add(newPlayer);
            rosterMap.put("John Doe", newPlayer);
        }
    }
    public Roster(int rosterSize) {
        roster = new ArrayList<>(rosterSize);
        setRoster("TeamsFolder/Detroit Tigers", rosterSize);
    }

    public Roster(File file) {
        roster = new ArrayList<>(DEFAULT_ROSTER_SIZE);
        rosterMap = new HashMap<>();
        try {
            Scanner teamInput = new Scanner(file);
            String currentPlayerName;
            String currentPlayerPosition;
            for (int i = 0; i < 26; i++) {
                if (teamInput.hasNext()) {
                    currentPlayerName = teamInput.nextLine();
                    currentPlayerPosition = teamInput.nextLine();
                } else {
                    break;
                }
                Player player = new Player(currentPlayerName, currentPlayerPosition);
                roster.add(player);
                rosterMap.put(currentPlayerName, player);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
    }

    public void setRoster(String filename, int rosterSize) {

            File teamFile = new File("C:\\Users\\lrsch\\IdeaProjects\\BaseballSimulation\\src\\TeamsFolder\\Detroit Tigers");
            System.out.println(teamFile);
            try {
                Scanner teamInput = new Scanner(teamFile);
                String currentPlayerName;
                String currentPlayerPosition;
                System.out.println(roster.size());
                for (int i = 0; i < rosterSize; i++) {
                    if (teamInput.hasNext()) {
                        currentPlayerName = teamInput.nextLine();
                        currentPlayerPosition = teamInput.nextLine();
                    } else {
                        break;
                    }
                    roster.add(new Player(currentPlayerName, currentPlayerPosition));
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
            System.out.println("Returning " + rosterMap.get(playerName).getPlayerName());
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



