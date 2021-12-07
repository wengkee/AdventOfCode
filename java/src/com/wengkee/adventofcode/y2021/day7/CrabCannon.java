package com.wengkee.adventofcode.y2021.day7;

import com.wengkee.adventofcode.util.Challenge;

import java.util.HashMap;

public class CrabCannon extends Challenge {

    private HashMap<Integer, Long> crabMap;
    private int lowestPosition = 0, highestPosition = 0;
    private boolean lowestSet = false, highestSet = false;

    public CrabCannon(int year, int day, int part, String input) {
        super(year, day, part, input);
        crabMap = new HashMap<>();
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            calculateLeastFuelPosition();
        }

        if ( getPart() == 2){
            calculateRealCrabMovement();
        }
    }

    private void calculateLeastFuelPosition(){
        initLoadCrab();

        long leastFuel = 0, leastFuelPosition = 0;
        boolean isFirst = true;
        for (int currPos = lowestPosition; currPos <= highestPosition; currPos++) {

            long totalFuelConsumed = 0;
            for (int posInMap : crabMap.keySet()){
                int diff = posInMap - currPos;
                diff = (diff < 0)? diff * -1 : diff;
                long fuel = crabMap.get(posInMap) * diff;
                totalFuelConsumed += fuel;
            }

            if (isFirst){
                isFirst = false;
                leastFuel = totalFuelConsumed;
                leastFuelPosition = currPos;
            } else if (totalFuelConsumed < leastFuel){
                leastFuel = totalFuelConsumed;
                leastFuelPosition = currPos;
            }
        }
        System.out.println("Answer is: " + leastFuel);
    }

    private void calculateRealCrabMovement(){
        initLoadCrab();

        long leastFuel = 0, leastFuelPosition = 0;
        boolean isFirst = true;
        for (int currPos = lowestPosition; currPos <= highestPosition; currPos++) {

            long totalFuelConsumed = 0;
            for (int posInMap : crabMap.keySet()){
                int diff = posInMap - currPos;
                diff = (diff < 0)? diff * -1 : diff;
                long fuel = crabMap.get(posInMap) * sumFuel(diff);
                totalFuelConsumed += fuel;
            }

            if (isFirst){
                isFirst = false;
                leastFuel = totalFuelConsumed;
                leastFuelPosition = currPos;
            } else if (totalFuelConsumed < leastFuel){
                leastFuel = totalFuelConsumed;
                leastFuelPosition = currPos;
            }
        }
        System.out.println("Answer is: " + leastFuel);
    }

    private void addCrabsIntoMap(int key, long addQty){
        if ( crabMap.containsKey(key) ){
            long qty = crabMap.get(key);
            crabMap.put(key, qty + addQty);
        } else {
            crabMap.put(key, addQty);
        }
    }

    private void initLoadCrab(){
        String[] data = getInputData().get(0).split(",");

        for (String str : data){
            int key = Integer.parseInt(str);
            if ( !lowestSet || key < lowestPosition){
                lowestPosition = key;
                lowestSet = true;
            }
            if ( !highestSet || key > highestPosition){
                highestPosition = key;
                highestSet = true;
            }
            addCrabsIntoMap(key, 1);
        }
    }

    private int sumFuel(int distance){
        if (distance > 0){
            return distance + sumFuel(distance-1);
        } else {
            return 0;
        }
    }

}
