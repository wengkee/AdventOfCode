package com.wengkee.adventofcode.y2020.day9;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.List;

public class EncodingProcessor extends Challenge {

    List<Long> numberList = new ArrayList<>();

    public EncodingProcessor(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {

        for (String data : getInputData()){
            numberList.add(Long.parseLong(data));
        }

        if ( this.getPart() == 1){
            int position = getInvalidNumPosition(25);
            System.out.println("Answer is: " + numberList.get(position));
        } else if ( this.getPart() == 2){
            int position = getInvalidNumPosition(25);
            findContiguous(position);
        }
    }

    private void findContiguous(int position){
        long invalidNum = numberList.get(position);
        for (int i = 0; i < position; i++) {
            long total = numberList.get(i);
            for (int j = i+1; j < position; j++) {
                total += numberList.get(j);
                if (total == invalidNum){
                    sumOfMinMax(i,j);
                    return;
                } else if ( total > invalidNum){
                    break;
                }
            }
        }
    }

    private void sumOfMinMax(int start, int stop){
        long min = numberList.get(start);
        long max = min;
        for (int i = start; i <= stop; i++) {

            long n = numberList.get(i);

            if ( n < min ){
                min = n;
            }

            if ( n > max ){
                max = n;
            }

        }
        System.out.println("Answer: " + (min+max));
    }

    private int getInvalidNumPosition(int preambleLength){


        for (int i = 0; i < numberList.size(); i++) {

            long num = numberList.get(i);
            if ( i >= preambleLength){

                if (!isValid(num, i - preambleLength, i - 1)){
//                    System.out.println("Invalid number: " + num);
                    return i;
                }
            }

        }

        return 0;
    }

    private boolean isValid(long num, int start, int end){
        for (int j = start; j <= end; j++) {
            for (int k = start; k < end; k++) {
                if ( j != k ){
                    long sum = numberList.get(j) + numberList.get(k);
                    if ( sum == num ){
//                            System.out.println(tmpList.get(j) + "," + tmpList.get(k) + ", num: " + num  + ", sum: " + sum );
                        return true;
                    }

                }
            }
        }
        return false;
    }
}
