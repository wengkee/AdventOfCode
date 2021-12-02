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

    private Challenge findChallenge(int year, int day, int part){
        for ( Challenge challenge : challengeList){
            if ( challenge.getYear() == year && challenge.getDay() == day && challenge.getPart() == part){
                return challenge;
            }
        }
        return null;
    }

    public void runChallenge(int year, int day, int part){
        Challenge challenge = findChallenge(year, day, part);

        System.out.println("##################################################");
        System.out.println("Challenge from year " + year + " day " + day + " part " + part );

        if (challenge != null){
            challenge.run();
        } else {
            System.out.println("Challenge not found, something is wrong! Please make sure the challenge is initialised. ");
        }

        System.out.println("##################################################");
    }
}
