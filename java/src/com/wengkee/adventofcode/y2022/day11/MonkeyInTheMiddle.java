package com.wengkee.adventofcode.y2022.day11;

import com.wengkee.adventofcode.util.Challenge;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyInTheMiddle extends Challenge {

    public MonkeyInTheMiddle(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        getMonkeyBusiness();
    }

    HashMap<Integer, Monkey> monkeyList = new HashMap<>();
    private void getMonkeyBusiness() {
        init();
        if (getPart() == 1) execRound(20, 3);
        if (getPart() == 2) execRound(10000, 1);
    }

    private void execRound(int round, long reduction){

        for (int i = 0; i < round; i++) {
//            System.out.println(i);

            List<Integer> listOfMonkeys = new ArrayList<>(monkeyList.keySet());
            Collections.sort(listOfMonkeys);

            for (int id : listOfMonkeys){

                Monkey m = monkeyList.get(id);

                for (Iterator<Item> it = m.itemList.iterator(); it.hasNext(); ){
                    Item item = it.next();
                    item.eval(m.instruction);
                    item.reduceWorry(reduction);
                    item.manageWorry();
                    m.inspected++;
                    if (item.worry % m.modulus == 0){
                        updateMap(m.ifTrue, item);
                    } else {
                        updateMap(m.ifFalse, item);
                    }
                    it.remove();
                }

            }
        }

        List<Long> mostActiveLs = new ArrayList<>();
        for ( int id : monkeyList.keySet()){
            Monkey monkey = monkeyList.get(id);
            mostActiveLs.add(monkey.inspected);
        }
        Collections.sort(mostActiveLs);
        Collections.reverse(mostActiveLs);

        System.out.println("\nAnswer: " + mostActiveLs.get(0)*mostActiveLs.get(1));
    }

    private void updateMap(int monkeyId, Item item){
        Monkey monkey = monkeyList.get(monkeyId);
        monkey.itemList.add(item);
    }

    private void init(){

        Monkey monkey = new Monkey();;

        for (int i = 0; i < getInputData().size(); i++) {
            String s = getInputData().get(i).trim();

            if (s.startsWith("Monkey")){
                monkey.id = Integer.parseInt(s.substring(s.length()-2, s.length()-1));
            }

            if (s.startsWith("Starting")){
                String[] parts = s.split(":")[1].split(",");
                for (String part : parts){
                    Item item = new Item(part.trim());
//                    monkey.itemList.add(item);
                    monkey.itemList.add(item);
                }
            }

            if (s.startsWith("Operation")){
                int idx = s.indexOf("old");
                monkey.instruction = s.substring(idx);
            }

            if (s.startsWith("Test")){
                monkey.modulus = extractLastNum(s, ".*by (\\d+)");
            }

            if (s.startsWith("If true")){
                monkey.ifTrue = extractLastNum(s, ".*monkey (\\d+)");
            }

            if (s.startsWith("If false")){
                monkey.ifFalse = extractLastNum(s, ".*monkey (\\d+)");
            }

            if (s.length() == 0 || i == getInputData().size() - 1){
                monkeyList.put(monkey.id, monkey);
                monkey = new Monkey();
            }
        }

        for (int id : monkeyList.keySet()){
            bigMod *= monkeyList.get(id).modulus;
        }
        System.out.println(bigMod);
    }

    int bigMod = 1;

    private int extractLastNum(String s, String pattern){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        if (m.find()){
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

    private class Monkey{

        List<Item> itemList;
        int id;
        int modulus;
        int ifTrue;
        int ifFalse;
        long inspected = 0;
        String instruction;

        public Monkey() {
            this.itemList = new ArrayList<>();
        }

        void print(){
            System.out.println("");
            System.out.println("== Monkey " + id);
            System.out.println("items size: " +itemList.size());
            for (Item item : itemList){
                System.out.print(item.worry + ", ");
            }
            System.out.println("");
            System.out.println("instruction: " +instruction);
            System.out.println("modulus: " +modulus);
            System.out.println("true: " +ifTrue);
            System.out.println("false: " +ifFalse);
            System.out.println("inspected: " +inspected);
        }
    }

    private class Item{

        long worry;

//        ScriptEngineManager manager = new ScriptEngineManager();
//        ScriptEngine engine = manager.getEngineByName("js");

        Item(String worry) {
            this.worry = Integer.parseInt(worry);
        }

        void reduceWorry(long reduction){
            worry = Math.floorDiv(worry, reduction) ;
        }

        void manageWorry(){
            worry %= bigMod;
        }

        void eval(String instruction){

            instruction = instruction.replaceAll("old", "" + worry);
            String[] parts = instruction.split(" ");

            if (parts[1].equals("+")){
                worry = Long.parseLong(parts[0]) + Long.parseLong(parts[2]);
            } else if (parts[1].equals("*")){
                worry = Long.parseLong(parts[0]) * Long.parseLong(parts[2]);
            }


//            try{
//                Object result = engine.eval(instruction);
//
//                if (result instanceof Integer){
//                    worry = (int) result;
//                } else if ( result instanceof Double){
//                    worry = (long) ( (double) result);
//                }
//
//            } catch (ScriptException e ){
//                System.out.println(e);
//            }
        }

    }

}
