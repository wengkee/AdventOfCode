package com.wengkee.adventofcode.y2022.day2;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RockPaperScissorProcessor extends Challenge {

    private List<Integer> ls = new ArrayList<>();
    private List<String> inputData = getInputData();

    public RockPaperScissorProcessor(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            checkScore();
        }

        if ( getPart() == 2){
            checkScoreAgain();
        }
    }

    private void checkScore(){
        int total = 0;
        for (String s : inputData){
            List<String> ls = List.of(s.split(" "));
            total += checkWinOrLose(ls.get(0), ls.get(1));
        }
        System.out.println(total);
    }

    private void checkScoreAgain(){
        int total = 0;
        for (String s : inputData){
            List<String> ls = List.of(s.split(" "));
            total += checkDecipher(ls.get(0), ls.get(1));
//            System.out.println(checkDecipher(ls.get(0), ls.get(1)));
        }
        System.out.println(total);
    }

    private int checkDecipher(String opp, String me) {
        int o = convertToPoints(opp);

        if( me.equals("X")){
            if(o == 1) return 3;
            if(o == 2) return 1;
            if(o == 3) return 2;
        }

        if( me.equals("Y")){
            return o + 3;
        }

        if( me. equals("Z")){
            if(o == 1) return 2 + 6;
            if(o == 2) return 3 + 6;
            if(o == 3) return 1 + 6;
        }

        return 0;

    }

    private int checkWinOrLose(String opp, String me){
        int m = convertToPoints(me);
        int o = convertToPoints(opp);

        int res = 0;

        if ( m == o ) return m + 3;

        if( (m == 1 && o == 3) || (m == 2 && o ==1) || (m==3 && o==2) ){
            return m + 6;
        }

        return m + res;
    }

    private int convertToPoints(String s){
        if(s.equals("A") || s.equals("X")) return 1; // rock
        if(s.equals("B") || s.equals("Y")) return 2; // paper
        if(s.equals("C") || s.equals("Z")) return 3; // scissor
        return 0;
    }

}
