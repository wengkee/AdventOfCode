package com.wengkee.adventofcode.y2020.day12;

import com.wengkee.adventofcode.util.Challenge;

public class RainRisk extends Challenge {

    public RainRisk(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        getManhattanDistance();
    }

    private void getManhattanDistance() {

        for (String s : getInputData()){

            String cmd = s.substring(0,1);
            int v = Integer.parseInt(s.substring(1,s.length()));

            if (cmd.equals("F")){
                move(getDirection(), v);

            } else if (cmd.equals("L") || cmd.equals("R")){
                adjustDegree(cmd, v);

            } else {
                move(cmd, v);
            }

            System.out.println("cmd: " + cmd + ", value: " + v + ", degree: " + degree + ", horizontal: " +  horizontal +", vertical: "+vertical);
        }
        System.out.println(Math.abs(horizontal)+Math.abs(vertical));
    }

    int degree = 90;
    int horizontal = 0;
    int vertical = 0;
    

    private void adjustDegree(String dir, int v){
        if (dir.equals("L")){
            degree -= v;
            if (degree < 0) {
                degree = 360 - Math.abs(degree);
            }
        } else {
            degree += v;
            degree = degree % 360;
        }
    }

    private String getDirection(){
        if (degree == 0) return "N";
        else if (degree == 90) return "E";
        else if (degree == 180) return "S";
        else if (degree == 270) return "W";
        return "";
    }

    private void move(String dir, int v){
        if (dir.equals("N")){
            vertical += v;
        } else if (dir.equals("S")){
            vertical -= v;
        } else if (dir.equals("W")){
            horizontal -= v;
        } else if (dir.equals("E")){
            horizontal += v;
        }
    }

}
