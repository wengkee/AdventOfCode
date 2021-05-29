package com.wengkee.adventofcode.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Challenge {

    private int day;
    private int part;
    private File input;

    protected Challenge(int day, int part, File input) {
        this.day = day;
        this.part = part;
        this.input = input;
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

    public List<String> getInputData() {
        List<String> data = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(this.input));

            String line;
            while ( (line = br.readLine()) != null) {
                data.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
