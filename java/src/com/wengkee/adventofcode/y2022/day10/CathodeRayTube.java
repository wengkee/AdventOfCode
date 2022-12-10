package com.wengkee.adventofcode.y2022.day10;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CathodeRayTube extends Challenge {

    public CathodeRayTube(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if (getPart() == 1) {
            getSignalStrength();
        }

        if (getPart() == 2) {
        }
    }

    int x = 1, cycle = 0, sumOfSignalStrength = 0;
    private void getSignalStrength() {

        for (int i = 0; i < getInputData().size(); i++) {

            String s = getInputData().get(i);

            if (s.startsWith("noop")){
                increaseCycle(1);
            }

            if ( s.startsWith("addx") ){
                increaseCycle(2);
                int v = Integer.parseInt(s.split(" ")[1]);
                x += v;
            }
        }
        System.out.println(sumOfSignalStrength);

    }

    private void increaseCycle(int n){
        for (int i = 0; i < n; i++) {
            cycle ++;
            if (cycle == 20 || (cycle - 20) % 40 == 0 ){
                int signalStr = cycle * x;
                sumOfSignalStrength += signalStr;
                System.out.println("MILESTONE::cycle: " + cycle + ", signalStr: " + signalStr);
            }
//            debug();
        }
    }

    private void increaseX(int v){
        x += v;

    }

    private void debug(){
        System.out.println("increasing cycle: " + cycle + ", x: " + x);
    }


}