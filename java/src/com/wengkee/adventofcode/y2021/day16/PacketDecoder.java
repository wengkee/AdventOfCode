package com.wengkee.adventofcode.y2021.day16;

import com.wengkee.adventofcode.util.Challenge;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PacketDecoder extends Challenge {

    private String binary;
    private int totalVersion = 0;

    public PacketDecoder(int year, int day, int part, String input) {
        super(year, day, part, input);
    }
    
    @Override
    public void run() {
        init();
        Packet packet = decode(binary);
        System.out.println("Total Version: " + totalVersion);
        System.out.println("Evaluated Value: " + packet.v);
    }

    private void init(){
        binary = hexToBin(getInputData().get(0));
        System.out.println(binary);
    }

    private String hexToBin(String hex){
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    private int getInt(String bin){
        return Integer.parseInt(bin, 2);
    }

    private long getLong(String bin){
        return Long.parseLong(bin, 2);
    }

    private int getVersion(String s){
        return getInt(s.substring(0, 3));
    }

    private int getTypeID(String s){
        return getInt(s.substring(0,3));
    }
     
    private Packet decode(String s){
        int version = getVersion(s);

        totalVersion += version;
        s = s.substring(3);

        int typeID = getTypeID(s);
        s = s.substring(3);

        if (typeID == 4){

            StringBuilder sb = new StringBuilder();
            while (true){
                String p = s.substring(0,1);
                sb.append(s, 1, 5);
                s = s.substring(5);
                if (p.equals("0")){
                    break;
                }
            }
            long v = getLong(sb.toString());
            return new Packet(v, s);

        } else {

            // operator
            String lengthTypeID = s.substring(0, 1);
            s = s.substring(1);
            List<Long> valueList = new ArrayList<>();

            if (lengthTypeID.equals("0")) {
                int lengthOfSubPacket = getInt(s.substring(0, 15));
                s = s.substring(15);
                String subPacket = s.substring(0, lengthOfSubPacket);
                while (subPacket.length() >= 11) { // 3 version + 3 type id + min 5 data
                    Packet packet = decode(subPacket);
                    subPacket = packet.str;
                    valueList.add(packet.v);
                }
                s = s.substring(lengthOfSubPacket);

            } else {

                int iteration = getInt(s.substring(0, 11));
                s = s.substring(11);
                for (int i = 0; i < iteration; i++) {
                    Packet packet = decode(s);
                    s = packet.str;
                    valueList.add(packet.v);
                }
            }


            long result;

            if (typeID == 0){
                result = valueList.stream().reduce(0L, Long::sum);
            } else if (typeID == 1){
                result = valueList.stream().reduce(1L, (a, b) -> a * b);
            } else if (typeID == 2){
                result = Collections.min(valueList);
            } else if (typeID == 3){
                result = Collections.max(valueList);
            } else if (typeID == 5){
                result = valueList.get(0) > valueList.get(1) ? 1 : 0;
            } else if (typeID == 6){
                result = valueList.get(0) < valueList.get(1) ? 1 : 0;
            }  else if (typeID == 7){
                result = valueList.get(0).equals(valueList.get(1))  ? 1 : 0;
            } else {
                throw new RuntimeException("Something is wrong..");
            }

            return new Packet(result, s);
        }
    }

    private static class Packet{

        private final Long v;
        private final String str;

        private Packet(long value, String s){
            this.v = value;
            this.str = s;
        }
    }
}


