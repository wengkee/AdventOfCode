package com.wengkee.adventofcode.y2021.day15;

import com.wengkee.adventofcode.util.Challenge;
import com.wengkee.adventofcode.util.Coordinate;

import java.util.*;

public class Chiton extends Challenge {

    private int[][] riskStack;
    private int xSize = 0, ySize = 0;

    public Chiton(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        init();
        findLowestRiskPath();
    }

    private HashMap<String, Integer> riskMap;
    private Queue<ChitonCoordinate> queue;

    private void findLowestRiskPath(){

        riskMap = new HashMap<>();
        queue = new PriorityQueue<>();
        queue.add(new ChitonCoordinate(0, 0, 0));

        while (true){
            ChitonCoordinate c = queue.poll();
            assert c != null;
            if (c.x == xSize-1 && c.y == ySize-1) {
                System.out.println("risk: " + c.risk);
                break;
            }
            getTotalRisk(c.y + 1, c.x, c.risk);
            getTotalRisk(c.y - 1, c.x, c.risk);
            getTotalRisk(c.y, c.x + 1, c.risk);
            getTotalRisk(c.y, c.x - 1, c.risk);
        }
    }

    private void getTotalRisk(int y, int x, int risk){

        if (getRisk(y,x) < 0) return;
        int newRisk = risk + getRisk(y,x);

        String key = y + "," + x;
        if (riskMap.containsKey(key) && riskMap.get(key) <= newRisk ) return;

        riskMap.put(key, newRisk);
        queue.add(new ChitonCoordinate(y, x, newRisk));

    }

    private int getRisk(int y, int x){
        if (y < 0 || y >= ySize) return -1;
        if (x < 0 || x >= xSize) return -1;
        return riskStack[y][x];
    }

    private void init(){

        ySize = getInputData().size();
        xSize = getInputData().get(0).length();
        riskStack = new int[ySize][xSize];

        for (int y = 0; y < ySize; y++) {
            String line = getInputData().get(y);
            List<String> values = Arrays.asList(line.split(""));
            for (int x = 0; x < xSize; x++) {
                int value = Integer.parseInt(values.get(x));
                riskStack[y][x] = value;
            }
        }

        if (getPart() == 2){

            int newYSize = ySize * 5;
            int newXSize = xSize * 5;

            int[][] newStack = new int[newYSize][newXSize];

            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    newStack[y][x] = riskStack[y][x];
                    populateX(newStack, new ChitonCoordinate(y, x, riskStack[y][x]), 1);
                }
            }

            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < newXSize; x++) {
                    populateY(newStack, new ChitonCoordinate(y, x, newStack[y][x]), 1);
                }
            }

            ySize = newYSize;
            xSize = newXSize;
            riskStack = newStack;
        }
//        printGrid(riskStack, ySize, xSize);
    }

    private void populateX(int[][] stack, ChitonCoordinate c, int cnt){
        if (cnt >= 5) return;
        int newRisk = c.risk + 1;
        if (newRisk == 10) newRisk = 1;
        ChitonCoordinate newC = new ChitonCoordinate(c.y, (c.x + xSize), newRisk);
        stack[newC.y][newC.x] = newC.risk;
        populateX(stack, newC, cnt+1);
    }

    private void populateY(int[][] stack, ChitonCoordinate c, int cnt){
        if (cnt >= 5) return;
        int newRisk = c.risk + 1;
        if (newRisk == 10) newRisk = 1;

        ChitonCoordinate newC = new ChitonCoordinate(c.y + ySize, c.x, newRisk);
        stack[newC.y][newC.x] = newC.risk;
        populateY(stack, newC, cnt+1);
    }

    private void printGrid(int[][] stack, int ySize, int xSize) {
        for(int i = 0; i < ySize; i++) {
            for(int j = 0; j < xSize; j++){
                System.out.printf("%2d ", stack[i][j]);
            }
            System.out.println();
        }
    }


    private static class ChitonCoordinate extends Coordinate implements Comparable<ChitonCoordinate>{

        private final int risk;

        private ChitonCoordinate(int y, int x, int risk){
            super(x, y);
            this.risk = risk;
        }

        @Override
        public int compareTo(ChitonCoordinate c) {
            return this.risk > c.risk ? 1 : -1;
        }
    }

}


