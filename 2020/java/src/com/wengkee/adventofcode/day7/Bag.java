package com.wengkee.adventofcode.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bag {

    private String color;
    private ArrayList<Bag> parentBags;
    private Map<Bag, Integer> childBags;

    public Bag(String color){
        this.color = color;
        this.parentBags = new ArrayList<>();
        this.childBags = new HashMap<>();
    }

    public String getColor() {
        return color;
    }

    public Map<Bag, Integer> getChildBags() {
        return childBags;
    }

    public void addChildBag(Bag childBag, int qty) {
        this.childBags.put(childBag, qty);
    }

    public void addParentBag(Bag parentBag) {
        this.parentBags.add(parentBag);
    }

    public ArrayList<Bag> getParents() {
        return this.parentBags;
    }

    public void printBag(){
        System.out.println("Bag: " + getColor());
        System.out.println("Parent: ");
        if ( parentBags != null && parentBags.size() > 0 ) {
            for (Bag parentBag : parentBags){
                System.out.println("\t" + parentBag.getColor());
            }
        }

        System.out.println("Child: ");
        if( childBags.size() > 0) {
            Iterator it = childBags.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry kv = (Map.Entry) it.next();
                Bag childBag = (Bag) (kv.getKey());
                System.out.println("\t" + childBag.getColor() + ": " + kv.getValue());
            }
        } else {
            System.out.println("\tEmpty.");
        }
    }

}
