package com.wengkee.adventofcode.y2022.day8;

import com.wengkee.adventofcode.util.Challenge;

import java.util.*;

public class TreeHouse extends Challenge {

    public TreeHouse(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        init();
        if (getPart() == 1) {
            checkVisibleTrees();
        }

        if (getPart() == 2) {
            calculateTreeScenicScores();
        }
    }

    private void calculateTreeScenicScores() {

        int highest = 0;
        for (int i = 0; i < isize; i++) {
            for (int j = 0; j < jsize; j++) {
//                if(isVisibleTree(i,j)) continue;
                int treeScores = checkTreeScores(i, j);
                if(treeScores > highest) highest = treeScores;
            }
        }
        System.out.println(highest);
    }

    private int checkTreeScores(int i, int j) {
        int v = grid[i][j];
        int n = 0, s = 0, w = 0, e = 0;

        // north
        for (int k = i-1; k >= 0; k--) {
            n++;
            if (grid[k][j] >= v) {
                break;
            }
        }

        // south
        for (int k = i+1; k < isize; k++) {
            s++;
            if (grid[k][j] >= v) {
                break;
            }
        }

        // west
        for (int k = j-1; k >= 0; k--) {
            w++;
            if (grid[i][k] >= v) {
                break;
            }
        }

        // east
        for (int k = j+1; k < jsize; k++) {
            e++;
            if (grid[i][k] >= v) {
                break;
            }
        }

        System.out.println(i + "," + j + ": treeScores: " + n*s*w*e + ", n: " + n + ", s: " + s + ", w: " + w + ", e: " + e);
        return n*s*w*e;
    }

    int[][] grid;
    int isize = 0, jsize = 0;
    private void init() {
        this.isize = getInputData().size();
        this.jsize = getInputData().get(0).length();
        this.grid = new int[isize][jsize];

        for (int i = 0; i < isize; i++) {
            for (int j = 0; j < jsize; j++) {
                grid[i][j] = Integer.parseInt((getInputData().get(i).charAt(j) + ""));
            }
        }
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int[] row : grid){
            sj.add(Arrays.toString(row));
        }
        System.out.println(sj);
    }

    private void checkVisibleTrees(){
        int cnt = 0;
        for (int i = 0; i < isize; i++) {
            for (int j = 0; j < jsize; j++) {
                if (isVisibleTree(i,j)){
                    cnt++;
                };
            }
        }
        System.out.println(cnt);
    }

    private boolean isVisibleTree(int i, int j){

        int v = grid[i][j];

        if (i == 0 || j == 0) return true;
        if (i == isize-1 || j == jsize -1) return true;

        // north
        for (int k = 0; k < i; k++) {
            if (grid[k][j] >= v) {
                break;
            } else if (k == i-1){
                System.out.println("north, " + i + "," + j + " = " + v);
                return true;
            }
        }


        // south
        for (int k = i+1; k < isize; k++) {
            if (grid[k][j] >= v) {
                break;
            } else if (k == isize-1){
                System.out.println("south, " + i + "," + j + " = " + v);
                return true;
            }
        }

        // west
        for (int k = 0; k < j; k++) {
            if (grid[i][k] >= v) {
                break;
            } else if (k == j-1){
                System.out.println("west, " + i + "," + j + " = " + v);
                return true;
            }
        }

        // east
        for (int k = j+1; k < jsize; k++) {
            if (grid[i][k] >= v) {
                break;
            } else if (k == jsize-1){
                System.out.println("east, " + i + "," + j + " = " + v);
                return true;
            }
        }


        return false;
    }
    
    

}