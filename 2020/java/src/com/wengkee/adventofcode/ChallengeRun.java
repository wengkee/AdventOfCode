package com.wengkee.adventofcode;

import com.wengkee.adventofcode.day7.BagProcessor;
import com.wengkee.adventofcode.day8.BootCodeProcessor;
import com.wengkee.adventofcode.util.ChallengeManager;

import java.io.File;

public class ChallengeRun {
    public static void main(String[] args) {

        ChallengeManager cm = new ChallengeManager();

        cm.addChallenge(new BagProcessor(7,1, getResourceFile("d7.txt")));
        cm.addChallenge(new BagProcessor(7,2, getResourceFile("d7.txt")));
        cm.addChallenge(new BootCodeProcessor(8,1, getResourceFile("d8.txt")));
        cm.addChallenge(new BootCodeProcessor(8,2, getResourceFile("d8aa.txt")));
        cm.runChallenge(8,2);
    }

    private static File getResourceFile(String filename){

        if ( filename != null ){
            try {
                return new File (ChallengeRun.class.getClassLoader().getResource(filename).getFile());
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }
}
