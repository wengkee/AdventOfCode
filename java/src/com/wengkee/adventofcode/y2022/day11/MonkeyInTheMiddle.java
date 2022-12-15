package com.wengkee.adventofcode.y2022.day11;

import com.wengkee.adventofcode.util.Challenge;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.*;
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
        execRound(1);
    }

    private void execRound(int round){
        for (int i = 0; i < round; i++) {

            Set<Integer> listOfMonkeys = monkeyList.keySet();

            for (Monkey monkey : monkeyList.values()){
                for (Item item : monkey.itemList){
                    item.eval(monkey.instruction);
                    item.reduceWorry();
                    if (item.worry % monkey.modulus == 0){

                    }
                }
            }
        }
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

//        for (Monkey m : monkeyList){
//            m.print();
//        }
    }

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
        }
    }

    private class Item{
        int worry;
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        Item(String worry) {
            this.worry = Integer.parseInt(worry);
        }

        void reduceWorry(){
            worry = Math.floorDiv(worry, 3) ;
            System.out.println(worry);
        }

        void eval(String instruction){
            instruction = instruction.replaceAll("old", "" + worry);
            try{
                Object result = engine.eval(instruction);
                worry = (int) result;
                System.out.println(worry);
            } catch (ScriptException e ){
                System.out.println(e);
            }
        }

    }

}
