package com.wengkee.adventofcode.y2021.day12;

import com.wengkee.adventofcode.util.Challenge;

import java.util.*;

public class PassagePathing extends Challenge {

    private HashMap<String, Node> allNodes;
    private HashSet<String> allPaths;
    private int maxVisit = 1;

    public PassagePathing(int year, int day, int part, String input) {
        super(year, day, part, input);
        this.allNodes = new HashMap<>();
        init();
    }

    @Override
    public void run() {
        if (getPart() == 2){
            this.maxVisit = 2;
        }
        getAllPaths();
    }

    private void getAllPaths(){
        allPaths = new HashSet<>();
        getPath("start", "start", new HashMap<>());

        List<String> sortedList = new ArrayList<>(allPaths);
        Collections.sort(sortedList);
        for (String path: sortedList){
            System.out.println(path);
        }
        System.out.println("Answer is: " + allPaths.size());
    }

    private void getPath(String curr, String path, HashMap<String, Integer> visitedSmallCave){

        int visit = 0;
        if (visitedSmallCave.containsKey(curr) ){
            for (int v : visitedSmallCave.values()){
                if (v >= maxVisit){
                    return;
                }
            }
            visit = visitedSmallCave.get(curr);
            if ( curr.equals("start") || visit >= maxVisit){
                return;
            }
        }

        if (curr.toLowerCase().equals(curr)) {
            visit++;
            visitedSmallCave.put(curr, visit);
        }

        Node node = allNodes.get(curr);
//        node.printNode();
        if (node.getNextNodeList() != null && node.getNextNodeList().size() > 0){
            for ( String next: node.getNextNodeList()){

                if (next.equals("end")){
                    allPaths.add(path + ",end");
                    continue;
                }

                if (allNodes.containsKey(next)) {
                    getPath(next, path + "," + next, new HashMap<>(visitedSmallCave));
                }
            }
        }

    }

    private void init(){
        for (int y = 0; y < getInputData().size(); y++) {
            String path = getInputData().get(y);
            processPath(path);
        }
    }

    private void processPath(String s){
        String a = s.split("-")[0];
        String b = s.split("-")[1];

        updateNode(a,b);
        updateNode(b,a);
    }

    private void updateNode(String a, String b){
        if (allNodes.containsKey(a)){
            allNodes.get(a).addNext(b);
        } else {
            allNodes.put(a, new Node(a, b));
        }
    }

    private class Node{

        private String name;
        private HashSet<String> nextNodeList;

        private Node(String name, String next){
            this.name = name;
            this.nextNodeList = new HashSet<>();
            addNext(next);
        }

        public HashSet<String> getNextNodeList() {
            return nextNodeList;
        }

        private void addNext(String s){
            this.nextNodeList.add(s);
        }

        private void printNode(){
            System.out.println("current: " + name + ", next nodes: " + nextNodeList);
        }

    }


}
