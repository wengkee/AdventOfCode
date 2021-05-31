package com.wengkee.adventofcode.day7;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BagProcessor extends Challenge {

    protected ArrayList<Bag> bagList;
    private static final String SHINY_GOLD_BAG = "shiny gold";

    public BagProcessor(int day, int part, String input) {
        super(day, part, input);
        this.bagList  = new ArrayList<>();
    }

    @Override
    public void run() {

        for ( String line : getInputData() ){
            process(line);
        }

        if ( getPart() == 1){
            findNumOfParents(SHINY_GOLD_BAG);
        } else if ( getPart() == 2 ){
            findNumOfChildBags(SHINY_GOLD_BAG);
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

    private void findNumOfParents(String color){

        Bag bag = findBag(color);
        if( bag != null){
            Set<Bag> result = new HashSet<>();
            traverseParents(bag, result);

            System.out.println("Answer: " + result.size());
        }

    }

    private void traverseParents(Bag bag, Set<Bag> result){
        if (bag.getParents() != null ){
            for (Bag parentBag : bag.getParents() ){
                result.add(parentBag);
                traverseParents(parentBag, result);
            }
        }
    }

    private void findNumOfChildBags(String color){

        Bag bag = findBag(color);

        if( bag != null){

            System.out.println("Answer: " + (traverseChildBags(bag) -1));
        }
    }

    private int traverseChildBags(Bag bag){

        int numOfBags = 1;

        for (Map.Entry<Bag, Integer> childBag : bag.getChildBags().entrySet() ){

            numOfBags += childBag.getValue() * traverseChildBags(childBag.getKey());
        }

        return numOfBags;

    }

}
