package com.wengkee.adventofcode.y2020.day12;

import com.wengkee.adventofcode.util.Challenge;

public class Day12_0 extends Challenge {

    //TODO: Still wip
    int x = 0, y = 0;
    int facing = 0;

    public Day12_0(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        getInputData().forEach(s -> {
            String instruction = s.substring(0, 1);
            int amount = Integer.parseInt(s.substring(1));
            if (instruction.equals("F")) {
                goForward(facingDirection(), amount);
            } else if (instruction.equals("N") || instruction.equals("S") || instruction.equals("E") || instruction.equals("W")) {
                goForward(instruction, amount);
            } else if (instruction.equals("R") || instruction.equals("L")) {
                changeDirection(instruction, amount);
            }

            int degree = ((facing+1)*90);
            if(degree == 360) degree = 0;
            System.out.println("cmd: " + instruction + ", value: " + amount + ", degree: " + facingDirection() + ", horizontal: " +  x +", vertical: "+ y);
        });
        System.out.println(Math.abs(x) + Math.abs(y));
    }

    private void changeDirection(String rotation, int amount) {
        amount = amount / 90;
        if (rotation.equals("R")) {
            facing = (amount + facing) % 4;
        } else {
            facing = Math.abs(amount - facing) % 4;
        }
    }

    private String facingDirection() {
        switch (facing) {
            case 0: return "E";
            case 1: return "S";
            case 2: return "W";
            case 3: return "N";
            default: return null;
        }
    }

    private void goForward(String direction, int amount) {
        switch (direction) {
            case "N":
                y += amount;
                break;
            case "S":
                y -= amount;
                break;
            case "W":
                x -= amount;
                break;
            case "E":
                x += amount;
                break;
        }
    }
}
