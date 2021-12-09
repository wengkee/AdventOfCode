package com.wengkee.adventofcode.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Challenge {

    private int year, day, part;
    private File input;
    private List<String> dataList;

    protected Challenge(int year, int day, int part, String input) {
        this.year = year;
        this.day = day;
        this.part = part;
        this.input = new File (getClass().getClassLoader().getResource(input).getFile());
        this.dataList = initInputData();
    }

    public int getYear() {
        return year;
    }
    public int getDay() {
        return day;
    }

    public int getPart() {
        return part;
    }

    public File getInput() {
        return input;
    }

    public abstract void run();

    public List<String> initInputData() {
        List<String> data = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(this.input));

            String line;
            while ( (line = br.readLine()) != null) {
                data.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("File does not exists");
        }

        return data;
    }

    public List<String> getInputData() {
        return this.dataList;
    }
}
