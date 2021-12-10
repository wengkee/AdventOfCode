package com.wengkee.adventofcode.y2021.day10;

import com.wengkee.adventofcode.util.Challenge;

import java.util.*;

public class SyntaxScoring extends Challenge {


    public SyntaxScoring(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if (getPart() == 1){
            getErrorScore();
        } else {
            getAutoCompleteMiddleScore();
        }
    }

    private void getErrorScore(){
        int errCount = 0;
        for (String line : getInputData()){
            List<String> openedList = new ArrayList<>();
            for (String s : line.split("")){
                if (isOpening(s)) {
                    openedList.add(s);
                } else {
                    String expected = getClosing(openedList.get(openedList.size()-1));
                    if (expected.equals(s)){
                        openedList.remove(openedList.size()-1);
                    } else {
                        errCount += getErrorScore(s);
                        break;
                    }
                }
            }
        }
        System.out.println(errCount);
    }

    private void getAutoCompleteMiddleScore(){
        List<Long> autocompleteScoreList = new ArrayList<>();
        for (String line : getInputData()){
            List<String> openedList = new ArrayList<>();
            boolean isCorrupted = false;
            for (String s : line.split("")){
                if (isOpening(s)) {
                    openedList.add(s);
                } else {
                    String expected = getClosing(openedList.get(openedList.size()-1));
                    if (expected.equals(s)){
                        openedList.remove(openedList.size()-1);
                    } else {
                        isCorrupted = true;
                        break;
                    }
                }
            }

            if (!isCorrupted){
//                System.out.println(openedList);
                long autocompleteScore = 0;
                if (openedList.size() > 0){
                    for (int i = openedList.size() - 1; i >= 0; i--) {
                        String opened = openedList.get(i);
                        autocompleteScore = (autocompleteScore * 5) + getAutoCompleteScore(getClosing(opened));
                    }
                    autocompleteScoreList.add(autocompleteScore);
                }
            }
        }
        System.out.println(autocompleteScoreList);
        Collections.sort(autocompleteScoreList);
        long median = autocompleteScoreList.get(autocompleteScoreList.size()/2);
        System.out.println(median);
    }

    private boolean isOpening(String s){
        return s.equals("(") || s.equals("[") || s.equals("{") || s.equals("<");
    }

    private String getClosing(String s){
        if ( s.equals("(") ) {
            return ")";
        } else if (s.equals("[")) {
            return "]";
        } else if (s.equals("{")){
            return "}";
        } else if (s.equals("<")){
            return ">";
        }
        return "";
    }

    private int getErrorScore(String s){
        if (s.equals(")")) return 3;
        if (s.equals("]")) return 57;
        if (s.equals("}")) return 1197;
        if (s.equals(">")) return 25137;
        return 0;
    }

    private int getAutoCompleteScore(String s){
        if (s.equals(")")) return 1;
        if (s.equals("]")) return 2;
        if (s.equals("}")) return 3;
        if (s.equals(">")) return 4;
        return 0;
    }

}
