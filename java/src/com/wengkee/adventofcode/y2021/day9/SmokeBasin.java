package com.wengkee.adventofcode.y2021.day9;

import com.wengkee.adventofcode.util.Challenge;

import java.util.*;

public class SmokeBasin extends Challenge {

    private Location[][] smokePlan;
    private int xSize, ySize;



    private HashMap<String, Location> checkedSpots;
    private HashMap<String, Location> uncheckedSpots;
    private List<Location> uncheckLocationList;
    private List<Location> checkedLocationList;

    List<Integer> basinList = new ArrayList<>();

    public SmokeBasin(int year, int day, int part, String input) {
        super(year, day, part, input);

        this.xSize = getInputData().get(0).length();
        this.ySize = getInputData().size();

        this.smokePlan = new Location[ySize][xSize];
        this.checkedSpots = new HashMap<>();
        this.uncheckedSpots = new HashMap<>();

        this.basinList = new ArrayList<>();
        init();
    }

    @Override
    public void run() {
        if (getPart() == 1){
            getRiskLevel();
        } else {
            getBasinLevel();
        }
    }

    private void getRiskLevel(){
        int risk = 0;

        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {

                int code = smokePlan[y][x].getCode();

                if (!isRisky(code, y, x)){
                    continue;
                }

                // reach here, means all are lower
                System.out.println("Code is risky: "  + code + ", at " + y +"," + x);
                risk += (code + 1);
            }
        }

        System.out.println("Answer is: " + risk);
    }

    private void getBasinLevel(){
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {

                Location location = smokePlan[y][x];
                int code = location.getCode();

                if (!isRisky(code, y, x)){
                    continue;
                }

                // reach here, means all are lower
                // find basin
                uncheckedSpots.put(location.getKey(), location);
                while (uncheckedSpots.size() > 0){

                    uncheckLocationList = new ArrayList<>();
                    checkedLocationList = new ArrayList<>();
                    for (String currKey : uncheckedSpots.keySet()){

                        Location currLocation = uncheckedSpots.get(currKey);
                        currLocation.scanBasin();
                        System.out.println("#####################");
                        System.out.println("Current: " + currLocation.getKey());
                        System.out.println("Checked: " + checkedSpots.keySet());
                        System.out.println("Unchecked: " + uncheckedSpots.keySet());
                        System.out.println();

                        currLocation.storeChecked();
                        checkedLocationList.add(currLocation);
//                        currLocation.removeUnchecked();
                    }

                    if (checkedLocationList != null && checkedLocationList.size() > 0){
                        System.out.println("checkedLocationList is not null.");
                        for (int i = 0; i < checkedLocationList.size(); i++) {
                            Location locInList = checkedLocationList.get(i);
                            uncheckedSpots.remove(locInList.getKey());
                            checkedLocationList.remove(i);
                            i--;
                        }
                    }

                    if (uncheckLocationList != null && uncheckLocationList.size() > 0){
                        System.out.println("uncheckLocationList is not null.");
                        for (int i = 0; i < uncheckLocationList.size(); i++) {
                            Location locInList = uncheckLocationList.get(i);
                            uncheckedSpots.put(locInList.getKey(), locInList);
                            uncheckLocationList.remove(i);
                            i--;
                        }
                    }

                }
                basinList.add(checkedSpots.size());
                System.out.println("DONE!!!! " + basinList.toString());
                checkedSpots.clear();

            }
        }

        Collections.sort(basinList);
        System.out.println("Answer is: "  + (basinList.get(basinList.size()-1) * basinList.get(basinList.size()-2) * basinList.get(basinList.size()-3)) );

    }

    private void init(){
        for (int y = 0; y < ySize; y++) {
            String line = getInputData().get(y);
            List<String> codes = Arrays.asList(line.split(""));
            for (int x = 0; x < xSize; x++) {
                int code = Integer.parseInt(codes.get(x));
                smokePlan[y][x] = new Location(y, x, code);
            }
        }
    }

    private boolean isRisky(int code, int y, int x){

        if ( y + 1 < ySize){
            int topCode = smokePlan[y+1][x].getCode();
            if (topCode <= code){
                return false;
            }
        }

        if ( y - 1 >= 0){
            int btmCode = smokePlan[y-1][x].getCode();
            if (btmCode <= code){
                return false;
            }
        }

        if ( x + 1 < xSize){
            int rightCode = smokePlan[y][x+1].getCode();
            if (rightCode <= code){
                return false;
            }
        }

        if ( x - 1 >= 0){
            int leftCode = smokePlan[y][x-1].getCode();
            if (leftCode <= code){
                return false;
            }
        }

        return true;
    }

    private class Location{

        int x, y;
        int code;
        String key;

        public Location(int y, int x, int code){
            this.y = y;
            this.x = x;
            this.code = code;
            this.key = y + "," + x;
        }

        public String getKey() {
            return key;
        }

        public int getCode() {
            return code;
        }

        public void storeUnchecked(){
            if (!checkedSpots.containsKey(this.getKey())){
//                uncheckedSpots.put(this.getKey(), this);
                uncheckLocationList.add(this);
                System.out.println(this.getKey() + " being stored into unchecked " + uncheckLocationList.size());
            }
        }

        public void removeUnchecked(){
            uncheckedSpots.remove(this.getKey());
            System.out.println(this.getKey() + " has been removed..");
        }

        public void storeChecked(){
            checkedSpots.put(this.getKey(), this);
        }

        public List<Location> scanBasin(){

            if (!checkedSpots.containsKey(this.getKey())){
                boolean topLeftExpandable = true, topRightExpandable = true;
                boolean btmLeftExpandable = true, btmRightExpandable = true;

                // scan top btm left right
                // add into list if not 9
                if ( y - 1 >= 0 ){ // top
                    Location topSpot = smokePlan[y-1][x];
                    if (topSpot.getCode() < 9){
                        topSpot.storeUnchecked();
                    } else {
                        topLeftExpandable = false;
                        topRightExpandable = false;
                    }
                }

                if ( y + 1 < ySize){ // bottom
                    Location btmSpot = smokePlan[y+1][x];
                    if (btmSpot.getCode() < 9){
                        btmSpot.storeUnchecked();
                    } else {
                        btmLeftExpandable = false;
                        btmRightExpandable = false;
                    }
                }

                if ( x - 1 >= 0){ // left
                    Location leftSpot = smokePlan[y][x-1];
                    if (leftSpot.getCode() < 9){
                        leftSpot.storeUnchecked();
                    } else {
                        topLeftExpandable = false;
                        btmLeftExpandable = false;
                    }
                }

                if ( x + 1 < xSize){ // right
                    Location rightSpot = smokePlan[y][x+1];
                    if (rightSpot.getCode() < 9){
                        rightSpot.storeUnchecked();
                    } else {
                        topRightExpandable = false;
                        btmRightExpandable = false;
                    }
                }

                // diagonal
                // if top and left are NOT 9, scan top left
                // if btm and left are NOT 9, scan btm left
                // if top and right are NOT 9, scan top right
                // if btm and right are NOT 9, scan btm right
                // if code is not 9 then add into list
                if (topLeftExpandable && y-1 >= 0 && x - 1 >= 0){
                    Location topLeftSpot = smokePlan[y-1][x-1];
                    if (topLeftSpot.getCode() < 9){
                        topLeftSpot.storeUnchecked();
                    }
                }

                if (btmLeftExpandable && y+1 < ySize && x - 1 >= 0){
                    Location btmLeftSpot = smokePlan[y+1][x-1];
                    if (btmLeftSpot.getCode() < 9){
                        btmLeftSpot.storeUnchecked();
                    }
                }

                if (topRightExpandable && y-1 >= 0 && x + 1 < xSize){
                    Location topRightSpot = smokePlan[y-1][x+1];
                    if (topRightSpot.getCode() < 9){
                        topRightSpot.storeUnchecked();
                    }
                }

                if (btmRightExpandable && y+1 < ySize && x + 1 < xSize){
                    Location btmRightSpot = smokePlan[y+1][x+1];
                    if (btmRightSpot.getCode() < 9){
                        btmRightSpot.storeUnchecked();
                    }
                }

                System.out.println("ABC " + uncheckLocationList.size());
                return uncheckLocationList;
            }

            return null;
        }
    }
}
