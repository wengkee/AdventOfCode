package com.wengkee.adventofcode.util;

import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {
    List<Challenge> challengeList;

    public ChallengeManager() {
        this.challengeList = new ArrayList<>();
    }

    public void addChallenge(Challenge challenge){
        challengeList.add(challenge);
    }

    private Challenge findChallenge(int day, int part){
        for ( Challenge challenge : challengeList){
            if ( challenge.getDay() == day && challenge.getPart() == part){
                return challenge;
            }
        }
        return null;
    }

    public void runChallenge(int day, int part){
        Challenge challenge = findChallenge(day, part);
        if (challenge != null){
            challenge.run();
        } else {
            System.out.println("Challenge not found, something is wrong! ");
        }
    }
}
