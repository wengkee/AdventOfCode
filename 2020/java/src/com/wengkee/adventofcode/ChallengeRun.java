package com.wengkee.adventofcode;

import com.wengkee.adventofcode.day11.SeatManager;
import com.wengkee.adventofcode.day7.BagProcessor;
import com.wengkee.adventofcode.day8.BootCodeProcessor;
import com.wengkee.adventofcode.day9.EncodingProcessor;
import com.wengkee.adventofcode.util.ChallengeManager;

public class ChallengeRun {
    public static void main(String[] args) {

        ChallengeManager cm = new ChallengeManager();

        cm.addChallenge(new BagProcessor(7,1, "d7.txt"));
        cm.addChallenge(new BagProcessor(7,2, "d7.txt"));
        cm.addChallenge(new BootCodeProcessor(8,1, "d8.txt"));
        cm.addChallenge(new BootCodeProcessor(8,2, "d8.txt"));
        cm.addChallenge(new EncodingProcessor(9,1, "d9.txt"));
        cm.addChallenge(new EncodingProcessor(9,2, "d9.txt"));
        cm.addChallenge(new SeatManager(11,1, "d11-test.txt"));
//        cm.runChallenge(7,1);
//        cm.runChallenge(7,2);
//        cm.runChallenge(8,1);
//        cm.runChallenge(9,2);
        cm.runChallenge(11,1);
    }
}
