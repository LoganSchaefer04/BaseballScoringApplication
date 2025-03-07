package com.baseballscoringapplication;

import com.baseballscoringapplication.gameComponents.Player;
import com.baseballscoringapplication.gameComponents.Roster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DefensiveLineup {
    Player[] lineup;

    public DefensiveLineup() {
        lineup = new Player[9];
    }

    public void setLineup(String[] playersList, Map<String, Player> rosterMap) {

        for (int i = 0; i < 9; i++) {
            lineup[i] = rosterMap.get(playersList[i]);
        }
    }

    public Player[] getDefensiveLineup() {
        return lineup;
    }
}
