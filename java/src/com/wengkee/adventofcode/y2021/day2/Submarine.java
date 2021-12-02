package com.wengkee.adventofcode.y2021.day2;

import com.wengkee.adventofcode.util.Challenge;

public class Submarine extends Challenge {

    public Submarine(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            getPosition();
        }

        if ( getPart() == 2){
            getPositionWithAim();
        }
    }

    private void getPosition(){
        int horizontal = 0, depth = 0;
        for (String data : getInputData()){
            String[] instructions = data.split(" ");

            String instruction = instructions[0].trim();
            int value = Integer.parseInt(instructions[1].trim());

            if (instruction.equals("forward")){
                horizontal += value;
            } else if ( instruction.equals("down")){
                depth += value;
            } else if ( instruction.equals("up")){
                depth -= value;
            }
        }

        System.out.println("Horizontal position is at : " + horizontal + ", Depth is at: " + depth);
        System.out.println("Answer is: " + horizontal*depth);

    }

    private void getPositionWithAim(){
        int horizontal = 0, depth = 0, aim = 0;
        for (String data : getInputData()){
            String[] instructions = data.split(" ");

            String instruction = instructions[0].trim();
            int value = Integer.parseInt(instructions[1].trim());

            if (instruction.equals("forward")){
                horizontal += value;
                depth += (aim * value);
            } else if ( instruction.equals("down")){
                aim += value;
            } else if ( instruction.equals("up")){
                aim -= value;
            }
        }

        System.out.println("Horizontal position is at : " + horizontal + ", Depth is at: " + depth);
        System.out.println("Answer is: " + horizontal*depth);
    }

}
