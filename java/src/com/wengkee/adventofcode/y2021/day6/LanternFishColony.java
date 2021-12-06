package com.wengkee.adventofcode.y2021.day6;

import com.wengkee.adventofcode.util.Challenge;

import java.util.HashMap;

public class LanternFishColony extends Challenge {

    private HashMap<Integer, Long> fishMap;

    public LanternFishColony(int year, int day, int part, String input) {
        super(year, day, part, input);
        fishMap = new HashMap<>();
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            progressDaysinMap(80);
        }

        if ( getPart() == 2){
            progressDaysinMap(256);
        }
    }

    private void progressDaysinMap(int progressByDays){
        loadFishesIntoMap();

        for (int i = 0; i < progressByDays; i++) {

            HashMap<Integer, Long> spawnMap = new HashMap<>();
            long resetQty = 0, spawnQty = 0;

            for (Integer key : fishMap.keySet()){
                long qty = fishMap.get(key);
                int newKey = key - 1;
                if ( newKey < 0 ){
                    resetQty = qty;
                    spawnQty = qty;
                } else {
                    spawnMap.put(newKey, qty);
                }
            }
            fishMap = spawnMap;
            // update day 6 and day 8
            addFishesIntoMap(6, resetQty);
            addFishesIntoMap(8, spawnQty);

            System.out.println("After " + (i+1) + " days: " + fishMap);
        }

        long totalFishes = 0;
        for (Integer key : fishMap.keySet()){
            totalFishes += fishMap.get(key);
        }



        System.out.println("Answer is: " + totalFishes);
    }

    private void addFishesIntoMap(int key, long addQty){
        if ( fishMap.containsKey(key) ){
            long qty = fishMap.get(key);
            fishMap.put(key, qty + addQty);
        } else {
            fishMap.put(key, addQty);
        }
    }

    private void loadFishesIntoMap(){
        System.out.println("Initial fishes: " + getInputData());
        String[] data = getInputData().get(0).split(",");

        for (String str : data){
            int key = Integer.parseInt(str);
            addFishesIntoMap(key, 1);
        }

        System.out.println("init: " + fishMap);
    }

}
