package com.wengkee.adventofcode.y2022.day4;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.List;

public class CampCleanupProcessor extends Challenge {

    private List<Integer> ls = new ArrayList<>();

    public CampCleanupProcessor(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if (getPart() == 1) {
            getInclusive();
        }

        if (getPart() == 2) {
            getOverlapping();
        }
    }

    private void getInclusive(){
        int cnt = 0;
        for (String s: getInputData()){
            List<String> ls = List.of(s.split(","));

            Section firstSec = new Section(ls.get(0));
            Section secondSec = new Section(ls.get(1));

            if (firstSec.isFullyInclusive(secondSec)) cnt++;
        }
        System.out.println(cnt);
    }

    private void getOverlapping(){
        int cnt = 0;
        for (String s: getInputData()){
            List<String> ls = List.of(s.split(","));

            Section firstSec = new Section(ls.get(0));
            Section secondSec = new Section(ls.get(1));

            if (firstSec.isOverlapping(secondSec)) cnt++;
        }
        System.out.println(cnt);

    }

    private class Section{
        int start = 0;
        int end = 0;

        public Section(String secAssignment){
            List<String> parts = List.of(secAssignment.split("-"));
            this.start = Integer.parseInt(parts.get(0));
            this.end = Integer.parseInt(parts.get(1));
        }

        public boolean isFullyInclusive(Section secondSec){

            if (start <= secondSec.start && end >= secondSec.end) return true;
            if (start >= secondSec.start && end <= secondSec.end) return true;

            return false;
        }

        public boolean isOverlapping(Section secondSec){
            if (start < secondSec.start && start < secondSec.end && end < secondSec.start && end < secondSec.end) return false;
            if (start > secondSec.start && start > secondSec.end && end > secondSec.start && end > secondSec.end) return false;
            return true;
        }

    }
}