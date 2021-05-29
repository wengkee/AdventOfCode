package com.wengkee.adventofcode;

import com.wengkee.adventofcode.day7.BagProcessor;
import com.wengkee.adventofcode.util.ChallengeManager;

import java.io.File;

public class ChallengeRun {
    public static void main(String[] args) {

        ChallengeManager cm = new ChallengeManager();

        cm.addChallenge(new BagProcessor(7,1, getResourceFile("d7.txt")));
        cm.addChallenge(new BagProcessor(7,2, getResourceFile("d7.txt")));
        cm.runChallenge(7,1);
    }

    private static File getResourceFile(String filename){

        if ( filename != null ){
            return new File (ChallengeRun.class.getClassLoader().getResource(filename).getFile());
        }

        return null;
    }
}
