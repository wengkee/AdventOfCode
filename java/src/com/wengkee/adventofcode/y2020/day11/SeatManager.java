package com.wengkee.adventofcode.y2020.day11;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.List;

public class SeatManager extends Challenge {

    List<List<Seat>> seatPlan;

    public SeatManager(int year, int day, int part, String input) {
        super(year, day, part, input);
        seatPlan = new ArrayList<>();
    }

    @Override
    public void run() {
        process(getInputData());
    }

    private void process(List<String> data){
        for (int i = 0; i < data.size(); i++) {
            String[] strings = data.get(i).split("");
            List<Seat> oneRow = new ArrayList<>();
            for (int j = 0; j < strings.length; j++) {
//                System.out.println("row: " + i + ", column: " + j + ", val: " + strings[j]);
                Seat seat = new Seat(strings[j]);
                oneRow.add(seat);
            }
            seatPlan.add(oneRow);
        }
        printSeatMap();
    }

    private void printSeatMap(){
        for (int row = 0; row < seatPlan.size(); row++) {
            for (int seatNo = 0; seatNo < seatPlan.get(row).size(); seatNo++) {
                System.out.print( (seatPlan.get(row).get(seatNo)).getType() );
            }
            System.out.println("");
        }
    }

    private class Seat{

        private String type;
        private boolean occupied = false;

        public Seat(String type) {
            this.type = type;
            if (type.equals("#")){
                this.occupied = true;
            }
        }

        public String getType() {
            return type;
        }

        public boolean isOccupied() {
            return occupied;
        }
    }
}
