package com.wengkee.adventofcode.y2021.day3;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.List;

public class Diagnostic extends Challenge {

    private int binaryNumberLength;

    public Diagnostic(int year, int day, int part, String input) {
        super(year, day, part, input);
        binaryNumberLength = getBinaryNumberLength();
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            getPowerConsumption();
        }

        if ( getPart() == 2){
            getLifeSupportRating();
        }
    }

    private void getPowerConsumption(){
        String gammaRate = "", epsilonRate = "";

        for (int i = 0; i < binaryNumberLength; i++) {
            if ( getMostCommonBit(getInputData(), i) == "1"){
                gammaRate = gammaRate + "1";
                epsilonRate = epsilonRate + "0";
            } else {
                gammaRate = gammaRate + "0";
                epsilonRate = epsilonRate + "1";
            }
        }
        System.out.println(gammaRate + ", " + epsilonRate);
        System.out.println( "Answer is: " + (Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2)) );
    }

    private void getLifeSupportRating(){

        List<String> ls = getInputData();
        // get oxygenGeneratorRating
        for (int i = 0; i < binaryNumberLength; i++) {
            ls = searchList(ls, getMostCommonBit(ls, i), i);
            if ( ls.size() == 1){
                break;
            }
        }
        String oxygenGeneratorRating = ls.get(0);

        ls = getInputData();
        for (int i = 0; i < binaryNumberLength; i++) {
            ls = searchList(ls, (getMostCommonBit(ls, i) == "1")?"0" : "1", i);
            if ( ls.size() <= 1){
                break;
            }
        }
        String co2ScrubberRating = ls.get(0);

        System.out.println( "Answer is: " + (Integer.parseInt(oxygenGeneratorRating, 2) * Integer.parseInt(co2ScrubberRating, 2)) );

    }

    private String getMostCommonBit(List<String> ls, int index){

        int countZero = 0, countOne = 0;

        for (int j = 0; j < ls.size(); j++) {
            if ( Character.toString(ls.get(j).charAt(index)).equals("0") ){
                countZero++;
            } else {
                countOne++;
            }
        }
        return (countOne >= countZero)? "1" : "0";
    }

    private int getBinaryNumberLength(){
        return getInputData().get(0).length();
    }

    private List<String> searchList(List<String> ls, String criteria, int index){

        List<String> results = new ArrayList<>();
        for (int i = 0; i < ls.size(); i++) {
            String data = ls.get(i);
            if( Character.toString(data.charAt(index)).equals(criteria) ){
                results.add(data);
            }
        }
        return results;
    }
}
