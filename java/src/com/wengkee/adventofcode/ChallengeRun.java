package com.wengkee.adventofcode;

import com.wengkee.adventofcode.util.ChallengeManager;
import com.wengkee.adventofcode.y2022.day1.CaloriesProcessor;
import com.wengkee.adventofcode.y2022.day2.RockPaperScissorProcessor;
import com.wengkee.adventofcode.y2022.day3.RucksackProcessor;
import com.wengkee.adventofcode.y2022.day4.CampCleanupProcessor;
import com.wengkee.adventofcode.y2022.day5.SupplyStackProcessor;
import com.wengkee.adventofcode.y2022.day6.TuningTroubleProcessor;
import com.wengkee.adventofcode.y2022.day7.NoSpaceLeftOnDevice;
import com.wengkee.adventofcode.y2022.day8.TreeHouse;

public class ChallengeRun {
    public static void main(String[] args) {

        ChallengeManager cm = new ChallengeManager();

        cm.addChallenge(new CaloriesProcessor(2022, 1,1, "2022/2022-d1.txt"));
        cm.addChallenge(new CaloriesProcessor(2022, 1,2, "2022/2022-d1.txt"));

        cm.addChallenge(new RockPaperScissorProcessor(2022, 2,1, "2022/2022-d2.txt"));
        cm.addChallenge(new RockPaperScissorProcessor(2022, 2,2, "2022/2022-d2.txt"));

        cm.addChallenge(new RucksackProcessor(2022, 3,1, "2022/2022-d3-test.txt"));
        cm.addChallenge(new RucksackProcessor(2022, 2,2, "2022/2022-d2.txt"));

        cm.addChallenge(new CampCleanupProcessor(2022, 4,1, "2022/2022-d4.txt"));
        cm.addChallenge(new CampCleanupProcessor(2022, 4,2, "2022/2022-d4.txt"));

        cm.addChallenge(new SupplyStackProcessor(2022, 5,1, "2022/2022-d5.txt"));
        cm.addChallenge(new SupplyStackProcessor(2022, 5,2, "2022/2022-d5.txt"));

        cm.addChallenge(new TuningTroubleProcessor(2022, 6,1, "2022/2022-d6.txt"));
        cm.addChallenge(new TuningTroubleProcessor(2022, 6,2, "2022/2022-d6.txt"));

        cm.addChallenge(new NoSpaceLeftOnDevice(2022, 7,1, "2022/2022-d7-test.txt"));
        cm.addChallenge(new NoSpaceLeftOnDevice(2022, 7,2, "2022/2022-d7.txt"));

        cm.addChallenge(new TreeHouse(2022, 8,1, "2022/2022-d8.txt"));
        cm.addChallenge(new TreeHouse(2022, 8,2, "2022/2022-d8.txt"));

        cm.runChallenge(2022,8,2);
    }
}