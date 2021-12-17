package com.wengkee.adventofcode.y2021.day17;

import com.wengkee.adventofcode.util.Challenge;
import com.wengkee.adventofcode.util.Coordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrickShot extends Challenge {

    private int minX, maxX;
    private int minY, maxY;

    public TrickShot(int year, int day, int part, String input) {
        super(year, day, part, input);
    }
    
    @Override
    public void run() {
        init();
        findVelocity();
    }

    private void findVelocity(){

        int highestY = 0;
        HashSet<String> distinctVelocity = new HashSet<>();

        for (int i = 1; i < maxX; i++) {
            for (int j = minY; j < -minY; j++) {

                ProbeCoordinate probe = new ProbeCoordinate(0,0);
                Velocity velocity = new Velocity(i, j);

                int tmpMaxY = 0;
                while (!probe.outOfRange()){
                    probe.move(velocity);
                    tmpMaxY = Math.max(probe.y, tmpMaxY);
                    if (probe.withinRange()){
                        highestY = Math.max(highestY, tmpMaxY);
                        distinctVelocity.add(i+","+j);
                    }
                }
            }
        }
        System.out.println("Highest Y: " + highestY);
        System.out.println("Distinct Velocity: " + distinctVelocity.size());
    }

    private void init(){
        String data = getInputData().get(0);

        Pattern pattern = Pattern.compile(".*x=(-?\\d+)..(-?\\d+).*y=(-?\\d+)..(-?\\d+).*");

        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            minX = Integer.parseInt(matcher.group(1));
            maxX = Integer.parseInt(matcher.group(2));
            minY = Integer.parseInt(matcher.group(3));
            maxY = Integer.parseInt(matcher.group(4));
        }
    }

    private class ProbeCoordinate extends Coordinate{

        public ProbeCoordinate(int x, int y) {
            super(x, y);
        }

        private void move(Velocity velocity){
            this.x += velocity.x;
            this.y += velocity.y;
            velocity.drag();
        }

        private boolean withinRange(){
            return x >= minX && x <= maxX && y >= minY && y <= maxY;
        }

        private boolean outOfRange(){
            return y < minY;
        }
    }

    private static class Velocity extends Coordinate{

        private Velocity(int x, int y) {
            super(x, y);
        }

        public void drag(){
            if (this.x > 0) x -= 1;
            if (this.x < 0) x += 1;
            this.y -= 1;
        }
    }

}


