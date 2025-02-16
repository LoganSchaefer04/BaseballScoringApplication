package com.baseballscoringapplication.gameComponents;

import com.baseballscoringapplication.gameComponents.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Pitcher extends Player {
    LinkedList<Pitch> pitchesList;
    float outsRecorded;

    public Pitcher(String name, String position) {
        super(name, position);
        pitchesList = new LinkedList<>();
    }
    @Override
    public void addPitch(Pitch pitch) {
        pitchesList.add(pitch);
    }
    @Override
    public int getPitchCount() {
        return pitchesList.size();
    }

    public void addOutsRecorded(int outsRecorded) {
        this.outsRecorded += outsRecorded;
    }
    @Override
    public float getInningsPitched() {
        float inningsPitched = 0;
        inningsPitched += (int) outsRecorded / 3;
        inningsPitched += (outsRecorded % 3) / 10;
        return inningsPitched;
    }
}
