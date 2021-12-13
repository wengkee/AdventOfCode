package com.wengkee.adventofcode.y2021.day13;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TransparentOrigami extends Challenge {

    private HashSet<String> coordinateList;
    private List<String> foldList;

    public TransparentOrigami(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        init();
        if (getPart()==1){
            foldAndGetDots(true);
            System.out.println("Answer is: " + coordinateList.size());
        } else {
            foldAndGetDots(false);
            printCode();
        }
    }

    private void foldAndGetDots(boolean breakAfterOneIteration){

        for (String fold : foldList){
            String type = fold.split("=")[0];
            int foldNum = Integer.parseInt(fold.split("=")[1]);

            for (String coordinate : new HashSet<>(coordinateList)){
                int x = Integer.parseInt(coordinate.split(",")[0]);
                int y = Integer.parseInt(coordinate.split(",")[1]);
                if (type.equals("y") && y > foldNum) {
                    coordinateList.remove(coordinate);
                    coordinateList.add(x + "," + (foldNum-(y-foldNum)));
                } else if (type.equals("x") && x > foldNum) {
                    coordinateList.remove(coordinate);
                    coordinateList.add((foldNum-(x-foldNum) + "," + y));
                }
            }

            if (breakAfterOneIteration) break;

        }
    }

    private void printCode(){
        int maxX = 0, maxY = 0;
        List<List<String>> coor2DList = new ArrayList<>();
        for (String coordinate : coordinateList){
            int x = Integer.parseInt(coordinate.split(",")[0]);
            int y = Integer.parseInt(coordinate.split(",")[1]);

            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        String[][] twoD = new String[maxY+1][maxX+1];
        for (String coordinate : coordinateList) {
            int x = Integer.parseInt(coordinate.split(",")[0]);
            int y = Integer.parseInt(coordinate.split(",")[1]);
            twoD[y][x] = "#";
        }

        System.out.println();
        for (int i = 0; i < maxY+1; i++) {
            for (int j = 0; j < maxX +1; j++) {
                String data = twoD[i][j];
                if (data == null || data.equals("")) data = " ";
                System.out.printf("%s ", data);
            }
            System.out.println();
        }
    }

    private void init(){
        this.coordinateList = new HashSet<>();
        this.foldList = new ArrayList<>();

        for (int y = 0; y < getInputData().size(); y++) {
            String line = getInputData().get(y);

            if (line.equals("")) continue;

            if (!line.startsWith("fold")){
                coordinateList.add(line);
            } else {
                String fold = line.split("along")[1].trim();
                foldList.add(fold);
            }
        }
    }

}
