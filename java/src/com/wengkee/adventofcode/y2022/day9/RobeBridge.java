package com.wengkee.adventofcode.y2022.day9;

import com.wengkee.adventofcode.util.Challenge;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RobeBridge extends Challenge {

    public RobeBridge(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if (getPart() == 1) {
            getVisitedPositions();
        }

        if (getPart() == 2) {
            snakeGetVisitedPositions();
        }
    }

    private void snakeGetVisitedPositions() {

        HashSet<String> visited = new HashSet<>();
        Knot head = new Knot();
        Knot tail = new Knot();

        List<Knot> bodies = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Knot body = new Knot();
            bodies.add(body);
        }

        for (String s : getInputData()){

            String[] parts = s.split(" ");

            String direction = parts[0];
            int numOfMoves = Integer.parseInt(parts[1]);

            System.out.printf("== %s ==\n", s);

            for (int i = 0; i < numOfMoves; i++) {
                head.move(direction);

                for (int j = 0; j < 8; j++) {
                    Knot body = bodies.get(j);

                    if (j==0) {
                        body.follow(head);
                    } else {
                        body.follow(bodies.get(j-1));
                    }
                }
                tail.follow(bodies.get(7));

                StringBuilder log = new StringBuilder();
                log.append("head: ");
                log.append( head.getCoor() + ", ");
                for (int j = 0; j < 8; j++) {
                    log.append("body" +  (j+1) +": ");
                    log.append( bodies.get(j).getCoor() + ", ");
                }
                log.append("tail: ");
                log.append( tail.getCoor() + ", ");
                System.out.println(log);
                visited.add(tail.i + "," + tail.j);
            }
        }

        System.out.println(visited.size());
    }

    private void getVisitedPositions() {

        HashSet<String> visited = new HashSet<>();
        Knot head = new Knot();
        Knot tail = new Knot();

        for (String s : getInputData()){

            String[] parts = s.split(" ");

            String direction = parts[0];
            int numOfMoves = Integer.parseInt(parts[1]);

            System.out.printf("== %s ==\n", s);

            for (int i = 0; i < numOfMoves; i++) {
                head.move(direction);
                tail.follow(head);
                visited.add(tail.i + "," + tail.j);
            }
        }

        System.out.println(visited.size());

    }

    private class Knot{

        int i=0;
        int j=0;

        String getCoor(){
            return i + "," + j;
        }

        void move(String s){
            if(s.equals("U")) i--;
            if(s.equals("D")) i++;
            if(s.equals("R")) j++;
            if(s.equals("L")) j--;
        }

        void follow(Knot head){
            int igap = head.i - this.i;
            int jgap = head.j - this.j;

            if ( (igap > 1 || igap < -1) && (jgap > 1 || jgap < -1) ){
                this.i += igap / 2;
                this.j += jgap / 2;
            } else if (igap > 1){

                i+=1;
                this.j = head.j;

            } else if (igap < -1){

                i-=1;
                this.j = head.j;

            } else if (jgap > 1){

                j+=1;
                this.i = head.i;
            } else if (jgap < -1){

                j-=1;
                this.i = head.i;
            }
        }
    }

}