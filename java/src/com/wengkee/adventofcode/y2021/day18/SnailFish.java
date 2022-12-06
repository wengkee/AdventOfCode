package com.wengkee.adventofcode.y2021.day18;

import com.wengkee.adventofcode.util.Challenge;
import com.wengkee.adventofcode.util.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONString;

public class SnailFish extends Challenge {

    public SnailFish(int year, int day, int part, String input) {
        super(year, day, part, input);
    }
    
    @Override
    public void run() {
        getMagnitude();
    }

    private void getMagnitude(){
//        while (true){
//            int changes = 0;
//            int depth = 0;
//            List<String> strList = Arrays.asList(str.split(""));
//            for (int i = 0; i < strList.size(); i++) {
//                String s = strList.get(i);
//                if (s.equals("[")) depth++;
//                if (isNum(s)){
//                    if (i+1 < strList.size()){
//                        String nextS = strList.get(i+1);
//                        if (isNum(nextS)){
//                            s += nextS;
//                            i++;
//                        }
//                    }
//                }
//            }
//
//        }
        JSONArray arr = new JSONArray(getInputData().get(0));

        System.out.println(arr);
        System.out.println(arr.length());

        JSONArray small = arr.getJSONArray(1);
        System.out.println(small);
        System.out.println(small.length());

        Object a = small.get(1);
        if (a instanceof JSONArray) return;
        System.out.println(a);
//        System.out.println(a.length());
    }

    private void init(){}

    private boolean isNum(String s){
        return ! s.equals("[") && s.equals("]") && s.equals(",");
    }

}


