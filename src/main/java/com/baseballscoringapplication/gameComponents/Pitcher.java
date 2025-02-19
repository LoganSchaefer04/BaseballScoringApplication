package com.baseballscoringapplication.gameComponents;

import com.baseballscoringapplication.gameComponents.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Pitcher extends Player {
    LinkedList<Pitch> pitchesList; // List of pitches pitcher throws in order.
    float outsRecorded; // Used to record how many innings pitcher has pitched.
    float inningsPitched;

    /**
     * Creates new instance of Pitcher with specified name and position.
     *
     * @param name name of pitcher.
     * @param position position of pitcher
     */
    public Pitcher(String name, String position) {
        super(name, position);
        pitchesList = new LinkedList<>();
    }

    /**
     * Adds pitch to list of pitcher's pitches.
     *
     * @param pitch pitch to add to list.
     */
    @Override
    public void addPitch(Pitch pitch) {
        pitchesList.add(pitch);
    }

    /**
     * Adds a specified number of outs to add to pitcher's stats.
     *
     * @param outsRecorded number of outs to add to pitcher's stats.
     */
    public void addOutsRecorded(int outsRecorded) {
        this.outsRecorded += outsRecorded;
        inningsPitched += outsRecorded / 3;
        inningsPitched += (outsRecorded % 3) / 10;
    }

    @Override
    public float getInningsPitched() {
        return inningsPitched;
    }

    @Override
    public int getPitchCount() {
        return pitchesList.size();
    }
}
