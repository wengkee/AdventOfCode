package com.wengkee.adventofcode.y2022.day3;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.List;

public class RucksackProcessor extends Challenge {

    private List<Integer> ls = new ArrayList<>();
    private List<String> inputData = getInputData();

    public RucksackProcessor(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if (getPart() == 1) {
            checkPrioritiesSum();
        }

        if (getPart() == 2) {
            checkGroupPriorities();
        }
    }

    private void checkPrioritiesSum() {

        int sum = 0;
        for (String s : inputData) {
            String[] parts = {s.substring(0, s.length() / 2), s.substring(s.length() / 2)};

            for (int i = 0; i < parts[0].length(); i++) {
                char c = parts[0].charAt(i);
                if (parts[1].contains("" + c)) {
                    sum += getPriorities(c);
                    break;
                }
            }

        }

        System.out.println(sum);
    }

    private void checkGroupPriorities() {

        int sum = 0, cnt = 0;
        List<String> ls = new ArrayList<>();
        for (String s : inputData) {

            ls.add(s);
            cnt++;

            if (cnt % 3 == 0) {
                cnt = 0;

                for (int i = 0; i < ls.get(0).length(); i++) {
                    char c = ls.get(0).charAt(i);
                    if (ls.get(1).contains("" + c) && ls.get(2).contains("" + c)) {
                        System.out.println(c);
                        sum += getPriorities(c);
                        ls.clear();
                        break;
                    }
                }
            }

        }
        System.out.println(sum);
    }

    private int getPriorities(char c) {

        if (Character.isLowerCase(c)) {
            return c - 96;
        }

        return c - 38;
    }
}