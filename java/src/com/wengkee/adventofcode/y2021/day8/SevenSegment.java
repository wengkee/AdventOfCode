package com.wengkee.adventofcode.y2021.day8;

import com.wengkee.adventofcode.util.Challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SevenSegment extends Challenge {

    public SevenSegment(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            getUniqueNum();
        }

        if ( getPart() == 2){
            getOutputNum();
        }
    }

    private void getUniqueNum(){
        int unique = 0;
        for (String data : getInputData()){
            String[] parts = data.split("\\|");
            String[] outputs = parts[1].trim().split(" ");
            for(String str : outputs){
                if (str.length() == 2 || str.length() == 4 || str.length() == 3 || str.length() == 7){
                    unique += 1;
                    System.out.println(str +", " + unique);
                }
            }
        }
        System.out.println("Answer is: " + unique);
    }


    private void getOutputNum(){
        long total = 0;
        for (String data : getInputData()){

            String[] parts = data.split("\\|");

            List<String> observations = new LinkedList<>(Arrays.asList(parts[0].trim().split(" ")));
            String[] outputs = parts[1].trim().split(" ");

            Reference reference = new Reference();
            boolean isBaseGotten = false;
            while (observations.size() > 0){

                for (int i = 0; i < observations.size(); i++) {
                    String s = observations.get(i);
                    if ( isBaseGotten
                            || s.length() == 2
                            || s.length() == 3
                            || s.length() == 4
                            || s.length() == 7 ){
                        String num = reference.getNum(s);
//                        System.out.println("s: " + s +", num: " + num);
                        observations.remove(i);
                        i -= 1;
                    }
                }
                isBaseGotten = true;
            }

            StringBuilder finalString = new StringBuilder();
            for (String output : outputs){
                finalString.append(reference.getNum(output));
//                System.out.println("Combi: " + output + ", " + reference.getNum(output));
            }
            System.out.println("Output: " + finalString.toString());
            total += Long.parseLong(finalString.toString());
        }
        System.out.println("Answer is: " + total);
    }

    private class Reference{
        private HashMap<String, String> refMap; // num, combination

        public Reference(){
            this.refMap = new HashMap<>();
        }

        public String getNum(String in){
            int len = in.length();

            if ( len == 2 ){
                this.refMap.put("1", in);
                return "1";
            }

            if ( len == 3){
                this.refMap.put("7", in);
                return "7";
            }

            if ( len == 4 ){
                this.refMap.put("4", in);
                return "4";
            }

            if ( len == 7){
                this.refMap.put("8", in);
                return "8";
            }

            // len 6 and include all alpha in 4 == 9
            // len 6 and include all alpha in 1 == 0
            // else is 6
            if ( len == 6 ){
                if ( sameCombination(in, "9") || containsAllChars(in, "4") ){
                    this.refMap.put("9", in);
                    return "9";
                } else if ( sameCombination(in, "0") || containsAllChars(in, "1") ){
                    this.refMap.put("0", in);
                    return "0";
                } else {
                    this.refMap.put("6", in);
                    return "6";
                }
            }

            // len 5 and include all alpha in 1 == 3
            // len 5 and all alpha is in 9 == 5
            // else is 2
            if (len == 5){
                if ( sameCombination(in, "3") || containsAllChars(in, "1") ){
                    this.refMap.put("3", in);
                    return "3";
                } else if ( sameCombination(in, "5") || allPartsOf(in, "9") ){
                    this.refMap.put("5", in);
                    return "5";
                } else {
                    this.refMap.put("2", in);
                    return "2";
                }
            }

            return "";
        }

        private boolean sameCombination(String combination, String expectedNum){
            if (refMap.containsKey(expectedNum) && refMap.get(expectedNum).equals(combination)){
                return true;
            }
            return false;
        }

        private boolean containsAllChars(String in, String ref){

            if (refMap.containsKey(ref)){
                for(String r: refMap.get(ref).split("")){
                    if (!in.contains(r)){
                        return false;
                    }
                }
                return true;
            }

            return false;
        }

        private boolean allPartsOf(String in, String ref){

            if (refMap.containsKey(ref)){
                String refString = refMap.get(ref);
                for(String s: in.split("")){
                    if (!refString.contains(s)){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    }

}
