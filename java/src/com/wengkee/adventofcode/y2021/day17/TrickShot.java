package com.wengkee.adventofcode.y2021.day17;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrickShot extends Challenge {

    public TrickShot(int year, int day, int part, String input) {
        super(year, day, part, input);
    }
    
    @Override
    public void run() {
        init();
        findVelocity();

    }

    private void findVelocity(){

        int maxTryY = (minY < 0)? minY * -1 : minY;
        System.out.println("maxTryY: " + maxTryY);
        System.out.println("minY: " + minY + ", maxY: " + maxY);


        int highestY = 0;
        HashSet<String> distinctVelocity = new HashSet<>();

        for (int i = -1000; i < 1000; i++) {

            for (int j = -1000; j < 1000; j++) {

                Coordinate probe = new Coordinate(0,0);
                Velocity velocity = new Velocity(i, j);

                int maxY = 0;
                while (!probe.outOfRange()){
                    probe.move(velocity);
                    maxY = Math.max(probe.y, maxY);
                    if (probe.withinRange()){
                        highestY = Math.max(highestY, maxY);
                        distinctVelocity.add(i+","+j);
                    }
                }
            }
        }
        System.out.println("highestY: " + highestY);
        System.out.println("distinct: " + distinctVelocity.size());
    }

    private void init(){
        String data = getInputData().get(0);

        Pattern pattern = Pattern.compile(".*x=(\\-?\\d+)..(\\-?\\d+).*y=(\\-?\\d+)..(\\-?\\d+).*");

        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            minX = Integer.parseInt(matcher.group(1));
            maxX = Integer.parseInt(matcher.group(2));
            minY = Integer.parseInt(matcher.group(3));
            maxY = Integer.parseInt(matcher.group(4));
        }
    }

    private int minX, maxX;
    private int minY, maxY;

    private class Coordinate{

        public int x;
        public int y;

        private Coordinate(int x, int y){
            this.x = x;
            this.y = y;
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

    private class Velocity extends Coordinate{

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


