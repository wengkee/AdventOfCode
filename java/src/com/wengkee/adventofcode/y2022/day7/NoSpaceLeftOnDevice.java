package com.wengkee.adventofcode.y2022.day7;

import com.wengkee.adventofcode.util.Challenge;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoSpaceLeftOnDevice extends Challenge {

    private List<Integer> ls = new ArrayList<>();

    public NoSpaceLeftOnDevice(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if (getPart() == 1) {
            findDirSize();
        }

        if (getPart() == 2) {
        }
    }

    private void findDirSize() {
        HashMap<Path, Integer> dirSizeMap = new HashMap<>();
        Path current = Path.of(File.separator);
        for (String cmd : getInputData()){
            if (cmd.startsWith("$ cd")){
                String dir = cmd.split(" ")[2];

                if (dir.equals("/")){
                    current = Path.of(File.separator);
                } else if (dir.equals("..")){
                    current = current.getParent();
                } else {
                    if (current.toString().equals(File.separator)){
                        current = Path.of(File.separator + dir);
                    } else {
                        current = Path.of(current.toString() + File.separator + dir);
                    }
                }
            } else if (cmd.startsWith("dir ")){
            } else if (!cmd.startsWith("$ ls")){
                int size = Integer.parseInt(cmd.split(" ")[0]);
                if (dirSizeMap.containsKey(current)){
                    size += dirSizeMap.get(current);
                }
                dirSizeMap.put(current, size);
            }
        }
        System.out.println(dirSizeMap);

        for (Path path: dirSizeMap.keySet()){
            for (Path anotherPath : dirSizeMap.keySet()){
                if (path.toString().equals(anotherPath.toString())) continue;
                if (path.toString().equals(File.separator) || anotherPath.toString().equals(File.separator)) continue;
                if (path.toString().contains(anotherPath.toString())){
                    System.out.println(anotherPath + ", " + dirSizeMap.get(anotherPath));
                    dirSizeMap.put(anotherPath, dirSizeMap.get(anotherPath) + dirSizeMap.get(path));
                    System.out.println(path + ", " + dirSizeMap.get(path));
                    System.out.println(anotherPath + ", " + dirSizeMap.get(anotherPath));
                }
            }
        }
        System.out.println(dirSizeMap);

        int sum = 0;
        for ( int v : dirSizeMap.values()){
            if (v <= 100000 ){
//                System.out.println(v);
                sum += v;
            }
        }
        System.out.println(sum);
    }


}