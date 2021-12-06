package com.wengkee.adventofcode.y2021.day6;

import com.wengkee.adventofcode.util.Challenge;
import com.wengkee.adventofcode.y2021.day5.HydrothermalVent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LanternFishColony extends Challenge {

    List<LanternFish> lanternFishList, spawnFishList;
    private HashMap<Integer, Long> fishMap;

    public LanternFishColony(int year, int day, int part, String input) {
        super(year, day, part, input);
        lanternFishList = new ArrayList<>();
        spawnFishList = new ArrayList<>();
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

    private void progressFishColony(int progressByDays){
        loadFishes();
        for (int i = 0; i < progressByDays; i++) {

            for (int j = 0; j < lanternFishList.size(); j++) {
                LanternFish fish = lanternFishList.get(j);
//                System.out.println("day " + i +", fish: " + fish.timer);
                fish.processTime();
            }
            lanternFishList.addAll(spawnFishList);
            spawnFishList.clear();
            System.out.println("day " + i + ", num of fishes: " + lanternFishList.size());
            printFishes();
        }
        System.out.println("Answer is: " + lanternFishList.size());
    }

    private void printFishes(){
        System.out.print("[");
        for (LanternFish fish : lanternFishList){
            System.out.print(fish.getTimer() + ", ");
        }
        System.out.print("]");
        System.out.println();
    }

    private void loadFishes(){
        System.out.println("Initial fishes: " + getInputData());
        String[] data = getInputData().get(0).split(",");

        for (String str : data){
            LanternFish fish = new LanternFish(Integer.parseInt(str.trim()));
            lanternFishList.add(fish);
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
            updateMap(6, resetQty);
            updateMap(8, spawnQty);

            System.out.println("After " + (i+1) + " days: " + fishMap);
        }

        long totalFishes = 0;
        for (Integer key : fishMap.keySet()){
            totalFishes += fishMap.get(key);
        }



        System.out.println("Answer is: " + totalFishes);
    }

    private void updateMap(int key, long addQty){
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
            updateMap(key, 1);
        }

        System.out.println("init: " + fishMap);
    }

    private class LanternFish{

        private int timer;
        public LanternFish(int timer){
            this.timer = timer;
        }

        public void processTime(){
            this.timer -= 1;
            if (this.timer < 0){
                this.timer = 6;
                spawnFishList.add(new LanternFish(8));
            }
        }

        public int getTimer() {
            return timer;
        }
    }

}
