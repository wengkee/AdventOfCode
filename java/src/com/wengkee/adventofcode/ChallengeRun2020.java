package com.wengkee.adventofcode;

import com.wengkee.adventofcode.util.ChallengeManager;
import com.wengkee.adventofcode.y2020.day12.Day12_0;
import com.wengkee.adventofcode.y2020.day12.RainRisk;
import com.wengkee.adventofcode.y2022.day1.CaloriesProcessor;
import com.wengkee.adventofcode.y2022.day10.CathodeRayTube;
import com.wengkee.adventofcode.y2022.day2.RockPaperScissorProcessor;
import com.wengkee.adventofcode.y2022.day3.RucksackProcessor;
import com.wengkee.adventofcode.y2022.day4.CampCleanupProcessor;
import com.wengkee.adventofcode.y2022.day5.SupplyStackProcessor;
import com.wengkee.adventofcode.y2022.day6.TuningTroubleProcessor;
import com.wengkee.adventofcode.y2022.day7.NoSpaceLeftOnDevice;
import com.wengkee.adventofcode.y2022.day8.TreeHouse;
import com.wengkee.adventofcode.y2022.day9.RobeBridge;

public class ChallengeRun2020 {
    public static void main(String[] args) {

        ChallengeManager cm = new ChallengeManager();

        cm.addChallenge(new RainRisk(2020, 12,1, "2020/2020-d12.txt")); // 3607
        cm.addChallenge(new RainRisk(2020, 12,2, "2020/2020-d12-test.txt"));

        cm.runChallenge(2020,12,1);
    }
}