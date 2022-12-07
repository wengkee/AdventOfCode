package com.wengkee.adventofcode.y2022.day5;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyStackProcessor extends Challenge {

    private List<Integer> ls = new ArrayList<>();

    public SupplyStackProcessor(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        init();
        if (getPart() == 1) {
            processStack();
        }

        if (getPart() == 2) {
            processList();
        }
    }

    int startOfInstruction;
    List<Stack> listOfStacks;
    private void init(){

        List<String> ls = new ArrayList<>();

        for (int i = 0; i < getInputData().size(); i++) {
            String s = getInputData().get(i);
            if(s.length() == 0 || s.equals("")) {
                startOfInstruction = i+1;
                break;
            }
            ls.add(s);
        }

        String lastRow = ls.get(ls.size()-1);
        int numOfStacks = Integer.parseInt(lastRow.charAt(lastRow.length()-1) + "");

        listOfStacks = new ArrayList<>();
        for (int i = 0; i < numOfStacks; i++) {
            Stack<String> stk = new Stack<>();
            for (int j = ls.size() - 2; j >= 0; j--) {
                int idx = 1 + (4*i); // 1 5 9
                if (ls.get(j).length() < idx) break;
                String s = "" + ls.get(j).charAt(idx);
                if (!s.equals("") && !s.equals(" ")){
                    stk.push(s);
                }
            }
            listOfStacks.add(stk);
        }
    }

    private void processStack(){

        for (int i = startOfInstruction; i < getInputData().size(); i++) {
            String s = getInputData().get(i);
            Instruction inst = new Instruction(s);
            for (int j = 0; j < inst.qty; j++) {
                Stack sourceStk = listOfStacks.get(inst.source - 1);
                String sj = (String) sourceStk.pop();

                Stack destStk = listOfStacks.get(inst.dest - 1);
                destStk.push(sj);
            }
        }
        System.out.println(listOfStacks);

        StringBuilder res = new StringBuilder();
        for (Stack listOfStack : listOfStacks) {
            String top = (String) listOfStack.pop();
            res.append(top);
        }

        System.out.println(res);

    }

    private void processList(){
        List<List<String>> ls = new ArrayList<>();
        for (Stack stk : listOfStacks){
            ls.add(new ArrayList<>(stk));
        }
        System.out.println(ls);
        for (int i = startOfInstruction; i < getInputData().size(); i++) {
            String s = getInputData().get(i);
            Instruction inst = new Instruction(s);

            List<String> sourceList = ls.get(inst.source -1 );
            List<String> destList = ls.get(inst.dest -1 );
            for (int j = inst.qty; j > 0; j--) {
                String sj = sourceList.get(sourceList.size()-j);
                destList.add(sj);
            }
            for (int j = inst.qty; j > 0; j--) {
                sourceList.remove(sourceList.size()-1);
            }
        }
        System.out.println(ls);
        StringBuilder s = new StringBuilder();
        for (List<String> l : ls){
            s.append(l.get(l.size()-1));
        }
        System.out.println(s);
    }

    private static class Instruction{
        // move (\d+) from (\d+) to (\d+)
        // move 1 from 2 to 1
        int qty, source, dest;

        Instruction(String s){
            Pattern r = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

            Matcher m = r.matcher(s);

            if (m.find()) {
                this.qty = Integer.parseInt(m.group(1));
                this.source = Integer.parseInt(m.group(2));
                this.dest = Integer.parseInt(m.group(3));

            } else {
                System.out.println("NO MATCH");
            }
        }
    }

}