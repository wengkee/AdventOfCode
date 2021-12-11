package com.wengkee.adventofcode.y2021.day11;

import com.wengkee.adventofcode.util.Challenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DumboOctopus extends Challenge {

    private final int xSize, ySize;
    private int[][] octopusMap;
    private Set<String> flashed;
    private int flashes;

    public DumboOctopus(int year, int day, int part, String input) {
        super(year, day, part, input);

        this.xSize = getInputData().get(0).length();
        this.ySize = getInputData().size();
        this.octopusMap = new int[ySize][xSize];
        init();
    }


    @Override
    public void run() {
        if (getPart() == 1){
            getFlashesAfterStep(100);
        } else {
            getSimultaneousFlash();
        }
    }

    private void getFlashesAfterStep(int step){
        flashes = 0;
        for (int i = 0; i < step; i++) {
            stepOctopuses();
            flashOctopuses();
            printGrid();
            System.out.println("********************************");
        }
        System.out.println("Flashes: " + flashes);
    }

    private void getSimultaneousFlash(){

        int i = 1;
        while (true){
            flashes = 0;

            stepOctopuses();
            flashOctopuses();
            printGrid();

            if (flashes == xSize * ySize){
                System.out.println("Curr step: " + i + ", Flashes: " + flashes + ", total num: " + xSize * ySize);
                break;
            }
            System.out.println("********************************");
            i++;
        }
    }

    private void init(){
        for (int y = 0; y < ySize; y++) {
            String line = getInputData().get(y);
            List<String> values = Arrays.asList(line.split(""));
            for (int x = 0; x < xSize; x++) {
                int value = Integer.parseInt(values.get(x));
                octopusMap[y][x] = value;
            }
        }
    }

    private void stepOctopuses(){
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                int v = getOctopus(y, x);
                if (v >= 0){
                    octopusMap[y][x] = v + 1;
                }
            }
        }
    }

    private void flashOctopuses(){
        flashed = new HashSet<>();
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (getOctopus(y, x) > 9){
                    flashOctopus(y, x);
                }
            }
        }
    }

    private void flashOctopus(int y, int x){

        int v = getOctopus(y, x);
        String key = y + "#" + x;
        if (flashed.contains(key) || v < 0) return;

        v++;
        octopusMap[y][x] = v;

        if (v > 9){
            flashed.add(key);
            flashes++;
            flashOctopus(y-1, x); // N
            flashOctopus(y+1, x); // S
            flashOctopus(y, x+1); // E
            flashOctopus(y, x-1); // W
            flashOctopus(y+1, x+1); // NE
            flashOctopus(y-1, x+1); // SE
            flashOctopus(y-1, x-1); // NW
            flashOctopus(y+1, x-1); // SW
            octopusMap[y][x] = 0;
        }
    }

    private int getOctopus(int y, int x){
        if (y < 0 || y >= ySize) return -1;
        if (x < 0 || x >= xSize) return -1;
        return octopusMap[y][x];
    }

    private void printGrid()
    {
        for(int i = 0; i < ySize; i++)
        {
            for(int j = 0; j < xSize; j++)
            {
                System.out.printf("%2d ", octopusMap[i][j]);
            }
            System.out.println();
        }
    }

}
