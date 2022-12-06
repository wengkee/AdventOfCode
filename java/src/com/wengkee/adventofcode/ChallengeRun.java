package com.wengkee.adventofcode;

import com.wengkee.adventofcode.y2020.day11.SeatManager;
import com.wengkee.adventofcode.y2020.day7.BagProcessor;
import com.wengkee.adventofcode.y2020.day8.BootCodeProcessor;
import com.wengkee.adventofcode.y2020.day9.EncodingProcessor;
import com.wengkee.adventofcode.util.ChallengeManager;
import com.wengkee.adventofcode.y2021.day1.SonarSweeperProcessor;
import com.wengkee.adventofcode.y2021.day10.SyntaxScoring;
import com.wengkee.adventofcode.y2021.day11.DumboOctopus;
import com.wengkee.adventofcode.y2021.day12.PassagePathing;
import com.wengkee.adventofcode.y2021.day13.TransparentOrigami;
import com.wengkee.adventofcode.y2021.day14.ExtendedPolymerization;
import com.wengkee.adventofcode.y2021.day15.Chiton;
import com.wengkee.adventofcode.y2021.day16.PacketDecoder;
import com.wengkee.adventofcode.y2021.day17.TrickShot;
import com.wengkee.adventofcode.y2021.day18.SnailFish;
import com.wengkee.adventofcode.y2021.day2.Submarine;
import com.wengkee.adventofcode.y2021.day3.Diagnostic;
import com.wengkee.adventofcode.y2021.day4.Bingo;
import com.wengkee.adventofcode.y2021.day5.HydrothermalVent;
import com.wengkee.adventofcode.y2021.day6.LanternFishColony;
import com.wengkee.adventofcode.y2021.day7.CrabCannon;
import com.wengkee.adventofcode.y2021.day8.SevenSegment;
import com.wengkee.adventofcode.y2021.day9.SmokeBasin;
import com.wengkee.adventofcode.y2022.day1.CaloriesProcessor;
import com.wengkee.adventofcode.y2022.day2.RockPaperScissorProcessor;
import com.wengkee.adventofcode.y2022.day3.RucksackProcessor;
import com.wengkee.adventofcode.y2022.day4.CampCleanupProcessor;

public class ChallengeRun {
    public static void main(String[] args) {

        ChallengeManager cm = new ChallengeManager();

        // cm.addChallenge(new BagProcessor(2020,7,1, "2020/2020-d7.txt"));
        // cm.addChallenge(new BagProcessor(2020,7,2, "2020/2020-d7.txt"));

        // cm.addChallenge(new BootCodeProcessor(2020,8,1, "2020/2020-d8.txt"));
        // cm.addChallenge(new BootCodeProcessor(2020,8,2, "2020/2020-d8.txt"));

        // cm.addChallenge(new EncodingProcessor(2020, 9,1, "2020/2020-d9.txt"));
        // cm.addChallenge(new EncodingProcessor(2020, 9,2, "2020/2020-d9.txt"));

        // cm.addChallenge(new SeatManager(2020, 11,1, "2020/2020-d11.txt"));
        // cm.addChallenge(new SeatManager(2020, 11,2, "2020/2020-d11.txt"));

        // cm.addChallenge(new SonarSweeperProcessor(2021, 1,1, "2021/2021-d1.txt"));
        // cm.addChallenge(new SonarSweeperProcessor(2021, 1,2, "2021/2021-d1.txt"));

        // cm.addChallenge(new Submarine(2021, 2,1, "2021/2021-d2.txt"));
        // cm.addChallenge(new Submarine(2021, 2,2, "2021/2021-d2.txt"));

        // cm.addChallenge(new Diagnostic(2021, 3,1, "2021/2021-d3.txt"));
        // cm.addChallenge(new Diagnostic(2021, 3,2, "2021/2021-d3.txt"));

        // cm.addChallenge(new Bingo(2021, 4,1, "2021/2021-d4.txt"));
        // cm.addChallenge(new Bingo(2021, 4,2, "2021/2021-d4.txt"));

        // cm.addChallenge(new HydrothermalVent(2021, 5,1, "2021/2021-d5.txt"));
        // cm.addChallenge(new HydrothermalVent(2021, 5,2, "2021/2021-d5.txt"));

        // cm.addChallenge(new LanternFishColony(2021, 6,1, "2021/2021-d6.txt"));
        // cm.addChallenge(new LanternFishColony(2021, 6,2, "2021/2021-d6.txt"));

        // cm.addChallenge(new CrabCannon(2021, 7,1, "2021/2021-d7.txt"));
        // cm.addChallenge(new CrabCannon(2021, 7,2, "2021/2021-d7.txt"));

        // cm.addChallenge(new SevenSegment(2021, 8,1, "2021/2021-d8.txt"));
        // cm.addChallenge(new SevenSegment(2021, 8,2, "2021/2021-d8.txt"));

        // cm.addChallenge(new SmokeBasin(2021, 9,1, "2021/2021-d9.txt"));
        // cm.addChallenge(new SmokeBasin(2021, 9,2, "2021/2021-d9.txt"));

        // cm.addChallenge(new SyntaxScoring(2021, 10,1, "2021/2021-d10.txt"));
        // cm.addChallenge(new SyntaxScoring(2021, 10,2, "2021/2021-d10.txt"));

        // cm.addChallenge(new DumboOctopus(2021, 11,1, "2021/2021-d11.txt"));
        // cm.addChallenge(new DumboOctopus(2021, 11,2, "2021/2021-d11.txt"));

        // cm.addChallenge(new PassagePathing(2021, 12,1, "2021/2021-d12.txt"));
        // cm.addChallenge(new PassagePathing(2021, 12,2, "2021/2021-d12.txt"));

        // cm.addChallenge(new TransparentOrigami(2021, 13,1, "2021/2021-d13.txt"));
        // cm.addChallenge(new TransparentOrigami(2021, 13,2, "2021/2021-d13.txt"));

        // cm.addChallenge(new ExtendedPolymerization(2021, 14,1, "2021/2021-d14.txt"));
        // cm.addChallenge(new ExtendedPolymerization(2021, 14,2, "2021/2021-d14.txt"));

        // cm.addChallenge(new Chiton(2021, 15,1, "2021/2021-d15.txt"));
        // cm.addChallenge(new Chiton(2021, 15,2, "2021/2021-d15.txt"));

        // cm.addChallenge(new PacketDecoder(2021, 16,1, "2021/2021-d16.txt"));
        // cm.addChallenge(new PacketDecoder(2021, 16,2, "2021/2021-d16.txt"));

        // cm.addChallenge(new TrickShot(2021, 17,1, "2021/2021-d17.txt"));
        // cm.addChallenge(new TrickShot(2021, 17,2, "2021/2021-d17.txt"));

        // cm.addChallenge(new SnailFish(2021, 18,1, "2021/d18-test.txt"));

//        cm.addChallenge(new CaloriesProcessor(2022, 1,1, "2022/2022-d1.txt"));
//        cm.addChallenge(new CaloriesProcessor(2022, 1,2, "2022/2022-d1.txt"));
//
//        cm.addChallenge(new RockPaperScissorProcessor(2022, 2,1, "2022/2022-d2.txt"));
//        cm.addChallenge(new RockPaperScissorProcessor(2022, 2,2, "2022/2022-d2.txt"));
//
//        cm.addChallenge(new RucksackProcessor(2022, 3,1, "2022/2022-d3-test.txt"));
//        cm.addChallenge(new RucksackProcessor(2022, 2,2, "2022/2022-d2.txt"));

        cm.addChallenge(new CampCleanupProcessor(2022, 4,1, "2022/2022-d4.txt"));
        cm.addChallenge(new CampCleanupProcessor(2022, 4,2, "2022/2022-d4.txt"));

        cm.runChallenge(2022,4,2);
    }
}