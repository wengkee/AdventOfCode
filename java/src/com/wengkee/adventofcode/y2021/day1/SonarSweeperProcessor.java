package com.wengkee.adventofcode.y2021.day1;

import com.wengkee.adventofcode.util.Challenge;

public class SonarSweeperProcessor extends Challenge {

    public SonarSweeperProcessor(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            getIncreaseCount();
        }

        if ( getPart() == 2){
            getWindowIncreaseCount();
        }
    }

    private void getIncreaseCount(){

        int prevNum = 0 ;
        int incrementCount = 0;

        for (String data : getInputData()){
            int num = Integer.parseInt(data);
            if ( prevNum != 0 && num > prevNum ){
                incrementCount++;
            }
            prevNum = num;
        }
        System.out.println("Answer is: " + incrementCount);

    }

    private void getWindowIncreaseCount(){
        int prevWindow = 0;
        int incrementCount = 0;
        for (int i = 0; i < getInputData().size(); i++) {
            if ( i < 2 ){
                continue;
            }

            int currentWindow = getDataAt(i) + getDataAt(i-1) + getDataAt(i-2);
//            System.out.println(currentWindow);
            if (prevWindow != 0 && currentWindow > prevWindow){
                incrementCount++;
            }
            prevWindow = currentWindow;
        }
        System.out.println("Answer is: " + incrementCount);
    }

    private int getDataAt(int index){
        return Integer.parseInt(getInputData().get(index));
    }
}
