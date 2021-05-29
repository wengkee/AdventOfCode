package com.wengkee.adventofcode.day7;

import com.wengkee.adventofcode.util.Challenge;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BagProcessor extends Challenge {

    protected ArrayList<Bag> bagList;

    public BagProcessor(int day, int part, File input) {
        super(day, part, input);
        this.bagList  = new ArrayList<>();
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            for ( String line : getInputData() ){
                process(line);
            }
            findAllParents("shiny gold");
        }
    }

    private void process(String rule) {

        String[] parts = rule.split("contain");
        String color = getBagColor(parts[0]);
        Bag bag = findBag(color);
        if (bag == null){
            bag = new Bag(color);
        }

        if (!rule.contains("no other bag")) {
            if (rule.contains("contain")) {

                String[] childBagsTxt = parts[1].split(",");

                for (String childBagTxt : childBagsTxt) {
                    Pattern pattern = Pattern.compile("(\\d)(.*)");
                    Matcher matcher = pattern.matcher(childBagTxt);

                    while (matcher.find()) {

                        int childQuantity = Integer.parseInt(matcher.group(1));
                        String childColor = getBagColor(matcher.group(2));

                        Bag childBag = findBag(childColor);

                        if (childBag == null) {
                            childBag = new Bag(childColor);
                            bagList.add(childBag);
                        }

                        childBag.addParentBag(bag);
                        bag.addChildBag(childBag, childQuantity);

                    }
                }
            }
        }

        bagList.add(bag);

    }

    private String getBagColor(String txt){
        return txt.substring(0, txt.indexOf("bag")).trim();
    }

    private Bag findBag(String color){

        for (Bag currBag : bagList){
            if (currBag.getColor().equals(color)){
                return currBag;
            }
        }

        return null;
    }

    public void printAllBags(){
        for (Bag currBag : this.bagList) {
            currBag.printBag();
            System.out.println("\n");
        }
    }

    public void findAllParents(String color){
        System.out.println("================= finding ==============");
        Bag bag = findBag(color);
        if( bag != null){
            ArrayList<Bag> result = new ArrayList<>();
            traverseParents(bag, result);

            System.out.println("Answer: " + result.size());
        }

    }

    private void traverseParents(Bag bag, ArrayList<Bag> result){
        if (bag.getParents() != null ){
            for (Bag parentBag : bag.getParents() ){

                if (!result.contains(parentBag)){
                    result.add(parentBag);
                }

                traverseParents(parentBag, result);
            }
        }
    }
}
