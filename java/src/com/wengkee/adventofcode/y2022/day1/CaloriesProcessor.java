package com.wengkee.adventofcode.y2022.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wengkee.adventofcode.util.Challenge;

public class CaloriesProcessor extends Challenge {

    private List<Integer> ls = new ArrayList<>();
    private List<String> inputData = getInputData();

    public CaloriesProcessor(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            getMaxCalories();
        }

        if ( getPart() == 2){
            getTopThree();
        }
    }


    private void getMaxCalories(){

        int tmp = 0, max =0;
        for( String s : inputData ){
            if(s.length() > 0 && !s.equals("")){
                int n = Integer.parseInt(s);
                tmp += n;
            } else {
                if( max < tmp ){
                    max = tmp;
                }
                tmp = 0;
            }
        }

        System.out.println(max);
    }

    private void getTopThree(){

        int tmp = 0;

        for (int i = 0; i < inputData.size(); i++) {

            String s = inputData.get(i);

            if(!isEmptyLine(s)){
                int n = Integer.parseInt(s);
                tmp += n;
            }

            if(i == inputData.size() - 1 || isEmptyLine(s)){
                ls.add(tmp);
                tmp = 0;
            }

        }

        Collections.sort(ls);
        System.out.println(ls);

        tmp = 0;
        for (int i = 0; i < 3; i++) {
            tmp += ls.get(ls.size()-i-1);
        }
        System.out.println(tmp);
    }

    private boolean isEmptyLine(String s){
        return s.length() <= 0 || s.equals("");
    }

}
