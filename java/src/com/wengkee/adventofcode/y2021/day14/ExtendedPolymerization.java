package com.wengkee.adventofcode.y2021.day14;

import com.wengkee.adventofcode.util.Challenge;

import java.util.*;

public class ExtendedPolymerization extends Challenge {

    private HashMap<String, Long> strMap;
    private HashMap<String, String> rulesMap;
    private HashMap<String, Long> charCountMap;
    public ExtendedPolymerization(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        init();
        if (getPart()==1){
            step(10);

        } else {
            step(40);
        }

        List<Long> ls = new ArrayList<>(charCountMap.values());
        Collections.sort(ls);

        System.out.println("Answer is: " +( ls.get(ls.size()-1)-ls.get(0) ));
    }

    private void init(){
        strMap = new HashMap<>();
        rulesMap = new HashMap<>();
        charCountMap = new HashMap<>();
        String str = getInputData().get(0);

        for (int i = 0; i < str.length(); i++) {
            if (i < str.length() - 1) { // skip last one as it is not enough to make a pair
                updateMap(strMap, str.substring(i, i + 2), 1);
            }
            updateMap(charCountMap, str.substring(i, i + 1), 1);
        }

        for (int i = 2; i < getInputData().size(); i++){
            String[] rules = getInputData().get(i).split(" -> ");
            rulesMap.put(rules[0], rules[1]);
        }

    }

    private void step(int steps){
        for (int i = 0; i < steps; i++) {
            HashMap<String, Long> tmpMap = new HashMap<>();

            for (String str: strMap.keySet()){
                long qty = strMap.get(str);
                if (rulesMap.containsKey(str)){
                    String newStr = rulesMap.get(str);
                    String s1 = str.charAt(0) + newStr;
                    String s2 = newStr + str.charAt(1);
                    updateMap(tmpMap, s1, qty);
                    updateMap(tmpMap, s2, qty);
                    updateMap(charCountMap, newStr, qty);
                } else {
                    updateMap(tmpMap, str, qty);
                }
            }

            strMap = tmpMap;
        }
    }

    private void updateMap(HashMap<String, Long> map, String k, long qty){
        long v = qty;
        if (map.containsKey(k)){
            v += map.get(k);
        }
        map.put(k, v);
    }

}


