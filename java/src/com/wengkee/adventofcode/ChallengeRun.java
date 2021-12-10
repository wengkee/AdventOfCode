package com.wengkee.adventofcode;

import com.wengkee.adventofcode.y2020.day11.SeatManager;
import com.wengkee.adventofcode.y2020.day7.BagProcessor;
import com.wengkee.adventofcode.y2020.day8.BootCodeProcessor;
import com.wengkee.adventofcode.y2020.day9.EncodingProcessor;
import com.wengkee.adventofcode.util.ChallengeManager;
import com.wengkee.adventofcode.y2021.day1.SonarSweeperProcessor;
import com.wengkee.adventofcode.y2021.day10.SyntaxScoring;
import com.wengkee.adventofcode.y2021.day2.Submarine;
import com.wengkee.adventofcode.y2021.day3.Diagnostic;
import com.wengkee.adventofcode.y2021.day4.Bingo;
import com.wengkee.adventofcode.y2021.day5.HydrothermalVent;
import com.wengkee.adventofcode.y2021.day6.LanternFishColony;
import com.wengkee.adventofcode.y2021.day7.CrabCannon;
import com.wengkee.adventofcode.y2021.day8.SevenSegment;
import com.wengkee.adventofcode.y2021.day9.SmokeBasin;

public class ChallengeRun {
    public static void main(String[] args) {

        ChallengeManager cm = new ChallengeManager();

        cm.addChallenge(new BagProcessor(2020,7,1, "2020-d7.txt"));
        cm.addChallenge(new BagProcessor(2020,7,2, "2020-d7.txt"));

        cm.addChallenge(new BootCodeProcessor(2020,8,1, "2020-d8.txt"));
        cm.addChallenge(new BootCodeProcessor(2020,8,2, "2020-d8.txt"));

        cm.addChallenge(new EncodingProcessor(2020, 9,1, "2020-d9.txt"));
        cm.addChallenge(new EncodingProcessor(2020, 9,2, "2020-d9.txt"));

        cm.addChallenge(new SeatManager(2020, 11,1, "2020-d11.txt"));
        cm.addChallenge(new SeatManager(2020, 11,2, "2020-d11.txt"));

        cm.addChallenge(new SonarSweeperProcessor(2021, 1,1, "2021-d1.txt"));
        cm.addChallenge(new SonarSweeperProcessor(2021, 1,2, "2021-d1.txt"));

        cm.addChallenge(new Submarine(2021, 2,1, "2021-d2.txt"));
        cm.addChallenge(new Submarine(2021, 2,2, "2021-d2.txt"));

        cm.addChallenge(new Diagnostic(2021, 3,1, "2021-d3.txt"));
        cm.addChallenge(new Diagnostic(2021, 3,2, "2021-d3.txt"));

        cm.addChallenge(new Bingo(2021, 4,1, "2021-d4.txt"));
        cm.addChallenge(new Bingo(2021, 4,2, "2021-d4.txt"));

        cm.addChallenge(new HydrothermalVent(2021, 5,1, "2021-d5.txt"));
        cm.addChallenge(new HydrothermalVent(2021, 5,2, "2021-d5.txt"));

        cm.addChallenge(new LanternFishColony(2021, 6,1, "2021-d6.txt"));
        cm.addChallenge(new LanternFishColony(2021, 6,2, "2021-d6.txt"));

        cm.addChallenge(new CrabCannon(2021, 7,1, "2021-d7.txt"));
        cm.addChallenge(new CrabCannon(2021, 7,2, "2021-d7.txt"));

        cm.addChallenge(new SevenSegment(2021, 8,1, "2021-d8.txt"));
        cm.addChallenge(new SevenSegment(2021, 8,2, "2021-d8.txt"));

        cm.addChallenge(new SmokeBasin(2021, 9,1, "2021-d9.txt"));
        cm.addChallenge(new SmokeBasin(2021, 9,2, "2021-d9.txt"));

        cm.addChallenge(new SyntaxScoring(2021, 10,1, "2021-d10.txt"));
        cm.addChallenge(new SyntaxScoring(2021, 10,2, "2021-d10.txt"));

        cm.runChallenge(2021,10,2);
    }
}