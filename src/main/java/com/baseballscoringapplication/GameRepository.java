package com.baseballscoringapplication;

import com.baseballscoringapplication.gameComponents.BaseballGame;

import java.util.List;
import java.util.ArrayList;

public class GameRepository {
    private List<BaseballGame> games = new ArrayList<>();
    public void addGame(BaseballGame game) {
        games.add(game);
    }

    public List<BaseballGame> getAllGames() {
        return games;
    }
}
