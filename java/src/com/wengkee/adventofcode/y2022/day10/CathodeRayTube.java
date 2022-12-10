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
            getSignalStrength();
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
                int v = Integer.parseInt(s.split(" ")[1]);
                increaseCycle(2);
                increaseX(v);
            }
        }
        System.out.println(sumOfSignalStrength);

    }

    int pixel = 0;
    private void increaseCycle(int n){
        for (int i = 0; i < n; i++) {
            cycle ++;

            int gap = pixel - x;
            if(gap >= -1 && gap <= 1){
                System.out.print("#");
            } else {
                System.out.print(".");
            }
            increasePixel();

            if (cycle == 20 || (cycle - 20) % 40 == 0 ){
                int signalStr = cycle * x;
                sumOfSignalStrength += signalStr;
            }
        }
    }

    private void increaseX(int v){
        x += v;
    }

    private void increasePixel(){
        pixel++;
        if (pixel == 40){
            pixel = 0;
            System.out.println("");
        }
    }

    private void debug(){
        System.out.println("increasing cycle: " + cycle + ", x: " + x);
    }


}
