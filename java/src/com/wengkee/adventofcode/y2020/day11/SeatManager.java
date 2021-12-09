package com.wengkee.adventofcode.y2020.day11;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatManager extends Challenge {

    private Seat[][] seatPlan;
    private int xSize, ySize;

    public SeatManager(int year, int day, int part, String input) {
        super(year, day, part, input);

        this.xSize = getInputData().get(0).length();
        this.ySize = getInputData().size();

        this.seatPlan = new Seat[ySize][xSize];
    }

    @Override
    public void run() {
        getOccupied();
    }

    private void getOccupied(){

        for (int i = 0; i < this.ySize; i++) {
            List<String> data = Arrays.asList(getInputData().get(i).split(""));
            for (int j = 0; j < this.xSize; j++) {
                seatPlan[i][j] = new Seat(data.get(j));
            }
        }

        takeSeat();
    }

    private void takeSeat(){

        int numOfChange, numOfOccupied;
        int tmp = 0;
        do {
            numOfChange = 0;
            numOfOccupied = 0;
            Seat[][] tempPlan = new Seat[this.ySize][this.xSize];
            for (int currY = 0; currY < this.ySize; currY++) {
                for (int currX = 0; currX < this.xSize; currX++) {
                    Seat seat = seatPlan[currY][currX];

                    if (seat.isSeat()){

                        if (!seat.isOccupied()){
                            if (isSeatReallyEmpty(currY, currX)){
                                tempPlan[currY][currX] = new Seat("#");
                                numOfChange++;
                                numOfOccupied++;
                                continue;
                            }
                        } else {
                            if (isSeatReallyFull(currY, currX)){
                                tempPlan[currY][currX] = new Seat("L");
                                numOfChange++;
                                continue;
                            }
                        }

                    }

                    if(seat.isOccupied()){
                        numOfOccupied++;
                    }
                    tempPlan[currY][currX] = seat;

                }
            }
            this.seatPlan = tempPlan;
//            printSeatMap(this.seatPlan);
//            System.out.println("===================================================");
            tmp++;
        } while ( numOfChange > 0 );
        System.out.println("Answer is: " + numOfOccupied);
    }
    
    private boolean isSeatReallyEmpty(int y, int x){

        if (getPart() == 2){
            return isSeatReallyEmptyExtended(y, x);
        }

        int minX = ( x-1 >= 0 )? x-1 : x;
        int maxX = ( x+1 < this.xSize)? x+1: x;

        int minY = ( y-1 >= 0)? y-1 : y;
        int maxY = ( y+1 < this.ySize)? y+1: y;


        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX ; j++) {
                if (seatPlan[i][j].isOccupied()){
                    return false;
                }
            }
        }

        return true;
    }
    
    private boolean isSeatReallyEmptyExtended(int y, int x){

        int minX = 0, maxX = this.xSize - 1;
        int minY = 0, maxY = this.ySize - 1;

        // check down
        for (int i = y+1; i <= maxY; i++) {
            Seat seat = seatPlan[i][x];
            if (seat.isSeat()){
                if (!seat.isOccupied()){
                    break;
                } else {
                    return false;
                }

            }
        }

        // check up
        for (int i = y-1; i >= 0; i--) {
            Seat seat = seatPlan[i][x];
            if (seat.isSeat()){
                if (!seat.isOccupied()){
                    break;
                } else {
                    return false;
                }

            }
        }

        // check left
        for (int i = x-1; i >= 0; i--) {

            Seat seat = seatPlan[y][i];
            if (seat.isSeat()){
                if (!seat.isOccupied()){
                    break;
                } else {
                    return false;
                }

            }
        }

        // check right
        for (int i = x+1; i <= maxX; i++) {

            Seat seat = seatPlan[y][i];
            if (seat.isSeat()){
                if (!seat.isOccupied()){
                    break;
                } else {
                    return false;
                }

            }
        }

        // check diagonal
        int cnt = 1; // skip 0 as this is orig seat
        boolean trChecked = false, brChecked = false, tlChecked = false, blChecked = false;   // to check all 4 diagonal direction
        while ( !trChecked || !brChecked || !tlChecked || !blChecked){

            // top right
            if ( !trChecked && y - cnt >= 0 && x + cnt <= maxX){
                Seat seat = seatPlan[y - cnt][x + cnt];

                if (seat.isSeat()){
                    if (seat.isOccupied()) {
                        return false;
                    } else {
                        trChecked = true;
                    }
                }

            } else {
                trChecked = true;
            }

            // bottom right
            if (  !brChecked && y + cnt <= maxY && x + cnt <= maxX){
                Seat seat = seatPlan[y + cnt][x + cnt];

                if (seat.isSeat()){
                    if (seat.isOccupied()) {
                        return false;
                    } else {
                        brChecked = true;
                    }
                }

            } else {
                brChecked = true;
            }

            // top left
            if ( !tlChecked &&  y - cnt >= 0 && x - cnt >= 0){
                Seat seat = seatPlan[y - cnt][x - cnt];

                if (seat.isSeat()){
                    if (seat.isOccupied()) {
                        return false;
                    } else {
                        tlChecked = true;
                    }
                }

            } else {
                tlChecked = true;
            }

            // bottom left
            if ( !blChecked && y + cnt <= maxY && x - cnt >= 0){
                Seat seat = seatPlan[y + cnt][x - cnt];

                if (seat.isSeat()){
                    if (seat.isOccupied()) {
                        return false;
                    } else {
                        blChecked = true;
                    }
                }

            } else {
                blChecked = true;
            }
            cnt++;
        }
        
        return true;
    }

    private boolean isSeatReallyFull(int y, int x){

        int threshold = 4;
        if (getPart() == 2){
            return isSeatReallyFullExtended(y, x);
        }

        int minX = ( x-1 >= 0 )? x-1 : x;
        int maxX = ( x+1 < this.xSize)? x+1: x;

        int minY = ( y-1 >= 0)? y-1 : y;
        int maxY = ( y+1 < this.ySize)? y+1: y;

        int count = 0;
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX ; j++) {
                Seat seat = seatPlan[i][j];

                if (i == y && j == x){
                    continue;
                }

                if (seat.isSeat() && seat.isOccupied()){
                    count++;
                    if (count >= threshold){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isSeatReallyFullExtended(int y, int x){

        int minX = 0, maxX = this.xSize - 1;
        int minY = 0, maxY = this.ySize - 1;
        int fullCnt = 0;

        // check down
        for (int i = y+1; i <= maxY; i++) {
            Seat seat = seatPlan[i][x];

            if (seat.isSeat()){
                if (seat.isOccupied()){
                    fullCnt++;
                }
                break;
            }
        }

        // check up
        for (int i = y-1; i >= 0; i--) {
            Seat seat = seatPlan[i][x];

            if (seat.isSeat()){
                if (seat.isOccupied()){
                    fullCnt++;
                }
                break;
            }
        }

        // check left
        for (int i = x-1; i >= 0; i--) {
            Seat seat = seatPlan[y][i];

            if (seat.isSeat()){
                if (seat.isOccupied()){
                    fullCnt++;
                }
                break;
            }
        }

        // check right
        for (int i = x+1; i <= maxX; i++) {
            Seat seat = seatPlan[y][i];

            if (seat.isSeat()){
                if (seat.isOccupied()){
                    fullCnt++;
                }
                break;
            }
        }

        // check diagonal
        int cnt = 1; // skip 0 as this is orig seat
        boolean trChecked = false, brChecked = false, tlChecked = false, blChecked = false;   // to check all 4 diagonal direction
        while ( !trChecked || !brChecked || !tlChecked || !blChecked){

            // top right
            if ( !trChecked && y - cnt >= 0 && x + cnt <= maxX){
                Seat seat = seatPlan[y - cnt][x + cnt];

                if (seat.isSeat()){
                    if (seat.isOccupied()){
                        fullCnt++;
                    }
                    trChecked = true;
                }
            } else {
                trChecked = true;
            }

            // bottom right
            if (  !brChecked && y + cnt <= maxY && x + cnt <= maxX){
                Seat seat = seatPlan[y + cnt][x + cnt];

                if (seat.isSeat()){
                    if (seat.isOccupied()){
                        fullCnt++;
                    }
                    brChecked = true;
                }
            } else {
                brChecked = true;
            }

            // top left
            if ( !tlChecked &&  y - cnt >= 0 && x - cnt >= 0){
                Seat seat = seatPlan[y - cnt][x - cnt];

                if (seat.isSeat()){
                    if (seat.isOccupied()){
                        fullCnt++;
                    }
                    tlChecked = true;
                }
            } else {
                tlChecked = true;
            }

            // bottom left
            if ( !blChecked && y + cnt <= maxY && x - cnt >= 0){
                Seat seat = seatPlan[y + cnt][x - cnt];

                if (seat.isSeat()){
                    if (seat.isOccupied()){
                        fullCnt++;
                    }
                    blChecked = true;
                }

            } else {
                blChecked = true;
            }
            cnt++;
        }


        if (fullCnt < 5){
            return false;
        }
        return true;
    }

    private void printSeatMap(Seat[][] plan){
        for(int i = 0; i < this.ySize; i++)
        {
            for(int j = 0; j < this.xSize; j++)
            {
                System.out.printf("%s ", plan[i][j].getType());
            }
            System.out.println();
        }
    }

    private class Seat{

        private String type;
        private boolean occupied = false;
        private boolean isSeat = true;

        public Seat(String type) {
            this.type = type;

            if(type.equals(".")) {
                this.isSeat = false;
            } else if(type.equals("#")){
                this.setOccupied(true);
            } else if(type.equals("L")){
                this.setOccupied(false);
            }
        }

        public String getType() {
            return type;
        }

        public void setOccupied(boolean occupied) {
            this.occupied = occupied;
            if (this.occupied){
                this.type = "#";
            } else {
                this.type = "L";
            }
        }

        public boolean isOccupied() {
            return occupied;
        }

        public boolean isSeat() {
            return isSeat;
        }
    }
}
