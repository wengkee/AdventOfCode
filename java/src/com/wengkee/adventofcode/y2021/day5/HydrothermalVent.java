package com.wengkee.adventofcode.y2021.day5;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HydrothermalVent extends Challenge {

    private List<Spot> spotList;
    private HashMap<String, Spot> spotMap;
    private int countDangerousSpot;

    public HydrothermalVent(int year, int day, int part, String input) {
        super(year, day, part, input);
        this.spotList = new ArrayList<>();
        this.countDangerousSpot = 0;
        this.spotMap = new HashMap<>();
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            getOverlap();
        }

        if ( getPart() == 2){
            getOverlapWithDiagonal();
        }
    }

    private void getOverlap(){

        for ( String data : getInputData() ){
            String[] line = data.split("->");
            
            Spot firstPos = new Spot(line[0].trim());
            Spot secondPos = new Spot(line[1].trim());

            processStraightLine(firstPos, secondPos);

        }
        System.out.println("Answer is: " + this.countDangerousSpot);
    }

    private void getOverlapWithDiagonal(){

        for ( String data : getInputData() ){
            String[] line = data.split("->");

            Spot firstPos = new Spot(line[0].trim());
            Spot secondPos = new Spot(line[1].trim());

            processStraightLine(firstPos, secondPos);
            processDiagonalLine(firstPos, secondPos);

        }
        System.out.println("Answer is: " + this.countDangerousSpot);
    }

    private void processStraightLine(Spot firstPos, Spot secondPos){
        int firstPosX = firstPos.getX();
        int firstPosY = firstPos.getY();

        int secondPosX = secondPos.getX();
        int secondPosY = secondPos.getY();

        if ( firstPosX == secondPosX || firstPosY == secondPosY ){

            int startX = Math.min(firstPosX, secondPosX);
            int endX = Math.max(firstPosX, secondPosX);

            int startY = Math.min(firstPosY, secondPosY);
            int endY = Math.max(firstPosY, secondPosY);

            for (int i = startX; i <= endX; i++) {
                for (int j = startY; j <= endY; j++) {
                    addSpot(i,j);
                }
            }

        }
    }

    private void processDiagonalLine(Spot firstPos, Spot secondPos){
        int firstPosX = firstPos.getX();
        int firstPosY = firstPos.getY();

        int secondPosX = secondPos.getX();
        int secondPosY = secondPos.getY();

        if ( firstPosX != secondPosX && firstPosY != secondPosY ){
            // diagonal
            int currX = firstPosX;
            int currY = firstPosY;
            boolean keepProcessing = true;
            while (true){
                addSpot(currX, currY);

                if (currX < secondPosX){
                    currX++;
                } else if (currX > secondPosX){
                    currX--;
                } else {
                    break;
                }

                if (currY < secondPosY){
                    currY++;
                } else if (currY > secondPosY){
                    currY--;
                } else {
                    break;
                }

            }

        }

    }

    private void addSpot(int x, int y){
        String key = x + "," + y;
        if (spotMap.containsKey(key)){
            Spot existingSpot = spotMap.get(key);
            existingSpot.increaseValue();
            if (existingSpot.getValue() == 2){
                countDangerousSpot++;
            }
            spotMap.put(key, existingSpot);
        } else {
            Spot currSpot = new Spot(x, y);
            currSpot.setValue(1);
            spotMap.put(key, currSpot);
        }

    }

    private class Spot {
        private int x, y;
        private int value;

        public Spot(String coordinatePair){
            decodeCoordinate(coordinatePair);
        }

        public Spot(int x, int y){
            this.x = x;
            this.y = y;
        }

        private void decodeCoordinate(String coordinatePair){
            String[] coordinates = coordinatePair.split(",");
            this.x = Integer.parseInt(coordinates[0]);
            this.y = Integer.parseInt(coordinates[1]);
        }

        @Override
        public boolean equals(Object obj) {
            Spot spot = (Spot) obj;
            return spot.x == this.x && spot.y == this.y;
        }

        public void increaseValue(){
            this.value += 1;
        }

        public void setValue(int value){
            this.value = value;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Spot{" +
                    "x=" + x +
                    ", y=" + y +
                    ", value=" + value +
                    '}';
        }
    }

}
