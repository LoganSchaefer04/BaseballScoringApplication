package com.baseballscoringapplication.gameComponents;

import java.util.ArrayList;
import java.util.Scanner;

public class Play {
    int strikeCount;
    int ballCount;
    // Pop-up, ground ball, line drive, fly ball, etc.
    String contactType;
    String contactResult;
    final String[] contactResultList = {"Single", "Double", "Triple", "Home Run", "Out"};
    final String[] contactList = {"Ground Ball", "Line Drive", "Fly Ball", "Pop Up"};
    ArrayList<Integer> defensiveSequence;
    int runsScored;
    int outs;
    ArrayList<Integer> runnersAdvancement;
    final String[] defendersList = {"P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF"};

    public Play(int numOuts, int[] basePaths) {
        outs = 0;
        runsScored = 0;
        defensiveSequence = new ArrayList<>();
        runnersAdvancement = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            runnersAdvancement.add(0);
        }
        Scanner playInput = new Scanner(System.in);
        beginPlayInput(playInput, basePaths);

    }

    public Play(int strikeCount, int ballCount) {
        if (strikeCount == 3) {
            outs = 1;
        } else if(ballCount == 4) {
            outs = 0;
        }
    }

    private void beginPlayInput(Scanner playInput, int[] basePaths) {

        System.out.println("Please choose contact type:");
        System.out.println("1) Ground Ball");
        System.out.println("2) Line Drive");
        System.out.println("3) Fly Ball");
        System.out.println("4) Pop up");
        contactType = contactList[playInput.nextInt() - 1];
        System.out.println(contactType);

        if (contactType.equals("Ground Ball")) {
            groundBall(playInput, basePaths);
        } else {
            ballInTheAir(playInput, basePaths);
        }
    }

    private void groundBall(Scanner playInput, int[] basePaths) {
        System.out.println("Where was the ball hit to: (Enter position as number)");
        System.out.print("1. P\n2. C\n3. 1B\n4. 2B\n5. 3B\n6. SS\n7. LF\n8. CF\n9. RF\n");
        int fieldingDefender = playInput.nextInt();
        defensiveSequence.add(fieldingDefender);
        playResult(playInput, basePaths, fieldingDefender);
    }

    private void ballInTheAir(Scanner playInput, int[] basePaths) {
        System.out.println("Unimplemented");
    }

    private void playResult(Scanner playInput, int[] basePaths, int fieldingDefender) {
        System.out.println("Please Enter the Result of the Play");
        System.out.println("1) Out");
        System.out.println("2) Single");
        System.out.println("3) Double");
        System.out.println("4) Triple");
        System.out.println("5) Home Run");

        switch(playInput.nextInt()) {
            case 1:
                break;
            case 2:
                single(basePaths);
            case 3:
                twoBagger(basePaths);
            case 4:
                triple(basePaths);
            case 5:
                homeRun(basePaths);
        }
    }

    private void single(int[] basePaths) {
        runnersAdvancement.replaceAll(n -> n + 1);
    }

    // Method name twoBagger as replacement for double
    private void twoBagger(int[] basePaths) {
        runnersAdvancement.replaceAll(n -> n + 2);
    }

    private void triple(int[] basePaths) {
        runnersAdvancement.replaceAll(n -> n + 2);
    }

    private void homeRun(int[] basePaths) {
        runnersAdvancement.replaceAll(n -> n + 4);
    }

    private void otherAdvancements(int[] basePaths, int outs) {
        System.out.println("Unimplemented");
    }




    public int getNumOuts() {
        return 1;
    }

    public ArrayList<Integer> getRunnersAdvancement() {
        return runnersAdvancement;
    }

    public int getRunsScored() {
        return runsScored;
    }
}
