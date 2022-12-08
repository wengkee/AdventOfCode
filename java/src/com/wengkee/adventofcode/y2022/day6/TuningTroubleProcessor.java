package com.wengkee.adventofcode.y2022.day6;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TuningTroubleProcessor extends Challenge {

    public TuningTroubleProcessor(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if (getPart() == 1) {
            getFirstMarker();
        }

        if (getPart() == 2) {
            getFirstMessageMarker();
        }
    }

    private void getFirstMarker(){
        String s = getInputData().get(0);

        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i+4 > s.length()) continue;
            String si = s.substring(i, i+4);
            if (!isRepeated(si)) {
                idx = i+4;
                break;
            }
        }
        System.out.println(idx);
        System.out.println(s.substring(0,idx));
    }

    private void getFirstMessageMarker(){
        String s = getInputData().get(0);

        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i+14 > s.length()) continue;
            String si = s.substring(i, i+14);
            if (!isRepeated(si)) {
                idx = i+14;
                break;
            }
        }
        System.out.println(idx);
        System.out.println(s.substring(0,idx));
    }

    private boolean isRepeated(String s){

        for (int i = 0; i < s.length(); i++) {
            String si = s.substring(i, i+1);
            if ( s.indexOf(si) != s.lastIndexOf(si)) return true;
        }

        return false;
    }


}