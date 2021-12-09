package com.wengkee.adventofcode.y2021.day9;

import com.wengkee.adventofcode.util.Challenge;

import java.util.*;

public class SmokeBasin extends Challenge {

    private int[][] smokePlan;
    private int xSize, ySize;
    private Set<String> visited;
    int basinSize;

    List<Integer> basinList;

    public SmokeBasin(int year, int day, int part, String input) {
        super(year, day, part, input);

        this.xSize = getInputData().get(0).length();
        this.ySize = getInputData().size();

        this.smokePlan = new int[ySize][xSize];
        this.basinList = new ArrayList<>();
        init();
    }

    @Override
    public void run() {
        if (getPart() == 1){
            getRiskLevel();
        } else {
            getBasinLevel();
        }
    }

    private void getRiskLevel(){
        int risk = 0;

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (isRisky(y,x)){
                    risk += ( getSpot(y, x) + 1);
                }
            }
        }

        System.out.println("Answer is: " + risk);
    }

    private void getBasinLevel(){
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {

                if (isRisky(y, x)){
                    visited = new HashSet<>();
                    basinSize = 0;
                    scanBasin(y, x);
                    basinList.add(basinSize);
                }

            }
        }

        Collections.sort(basinList);
        System.out.println("Answer is: "  + (basinList.get(basinList.size()-1) * basinList.get(basinList.size()-2) * basinList.get(basinList.size()-3)) );

    }

    private void init(){
        for (int y = 0; y < ySize; y++) {
            String line = getInputData().get(y);
            List<String> codes = Arrays.asList(line.split(""));
            for (int x = 0; x < xSize; x++) {
                int code = Integer.parseInt(codes.get(x));
                smokePlan[y][x] = code;
            }
        }
    }

    private int getSpot(int y, int x){
        if (y < 0 || y >= ySize) return 999;
        if (x < 0 || x >= xSize) return 999;
        return smokePlan[y][x];
    }

    private boolean isRisky(int y, int x){
        int me = getSpot(y, x);
        int n = getSpot(y-1, x);
        int s = getSpot(y+1, x);
        int e = getSpot(y, x+1);
        int w = getSpot(y, x-1);
        return  me < n &&
                me < s &&
                me < w &&
                me < e;
    }

    private void scanBasin(int y, int x){
        String key = y + "#" + x;
        if (visited.contains(key)) return;
        visited.add(key);
        int code = getSpot(y,x);
        if (code < 9){
            basinSize++;
            scanBasin(y-1, x);
            scanBasin(y+1, x);
            scanBasin(y, x+1);
            scanBasin(y, x-1);
        }
    }
}
