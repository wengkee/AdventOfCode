package com.wengkee.adventofcode;

import com.wengkee.adventofcode.day7.BagProcessor;
import com.wengkee.adventofcode.util.Challenge;

import java.io.File;

public class ChallengeRun {
    public static void main(String[] args) {
        Challenge c7p1 = new BagProcessor(7,1, getFile("d7.txt"));
        c7p1.run();
    }

    private static File getFile(String filename){

        if ( filename != null ){
            return new File (ChallengeRun.class.getClassLoader().getResource(filename).getFile());
        }

        return null;
    }
}
