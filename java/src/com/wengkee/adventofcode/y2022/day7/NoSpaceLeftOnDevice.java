package com.wengkee.adventofcode.y2022.day7;

import com.wengkee.adventofcode.util.Challenge;

import java.util.*;

public class NoSpaceLeftOnDevice extends Challenge {

    HashMap<String, Integer> dirSizeMap = new HashMap<>();

    public NoSpaceLeftOnDevice(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        init();
        if (getPart() == 1) {
            findDirSize();
        }

        if (getPart() == 2) {
            freeUpDisk();
        }
    }

    private void freeUpDisk() {
        int freeSize = 70000000 - dirSizeMap.get("/");
        int needToFreeUp = 30000000 - freeSize;

        Integer smallest = null;
        for (int dirSize : dirSizeMap.values()){
            if (dirSize > needToFreeUp){
                if (smallest == null || dirSize < smallest){
                    smallest = dirSize;
                }
            }
        }
        System.out.println(smallest);
    }

    private void findDirSize() {
        int sum = 0;
        for ( int v : dirSizeMap.values()){
            if (v <= 100000 ){
                sum += v;
            }
        }
        System.out.println(sum);
    }

    private void init() {

        List<String> ls = new ArrayList<>();

        for (String cmd : getInputData()){

            if (cmd.startsWith("$ cd")){

                String dir = cmd.split(" ")[2];
                if (dir.equals("/")){
                    ls.clear();
                } else if (dir.equals("..")){
                    ls.remove(ls.size()-1);
                } else {
                    ls.add(dir);
                }
            } else if (cmd.matches("^\\d+ .+")){
                int size = Integer.parseInt(cmd.split(" ")[0]);
                update(dirSizeMap, "/", size);

                StringBuilder path = new StringBuilder("/");
                for (int i = 0; i < ls.size(); i++) {
                    path.append(ls.get(i));
                    path.append("/");
                    update(dirSizeMap, path.toString(), size);
                }
            }
        }
        System.out.println(dirSizeMap);
    }

    private void update(HashMap<String, Integer> map, String k, int v){
        if (map.containsKey(k)){
            v += map.get(k);
        }
        map.put(k, v);
    };

}