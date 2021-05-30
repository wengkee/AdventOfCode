package com.wengkee.adventofcode.day8;

import com.wengkee.adventofcode.util.Challenge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BootCodeProcessor extends Challenge {

    private static final String ACC = "acc";
    private static final String NOP = "nop";
    private static final String JMP = "jmp";

    List<BootCode> bootCodeList;

    public BootCodeProcessor(int day, int part, File input) {
        super(day, part, input);
        bootCodeList = new ArrayList<>();
    }

    @Override
    public void run() {
        for ( String line : getInputData() ){
            process(line);
        }

        if ( getPart() == 1){
            isBootingBroken(true);
        } else if ( getPart() == 2 ){
            fixAndFindAccumulator();
        }
    }

    private void process(String line){
        String[] parts = line.split(" ");
        bootCodeList.add(new BootCode(parts[0], Integer.parseInt(parts[1]) ));
    }

    private void fixAndFindAccumulator(){

        for (int i = 0; i < bootCodeList.size(); i++) {

            BootCode bootCode = bootCodeList.get(i);
            String oldType = bootCode.getType();
            if (bootCode.getType().equals(JMP)){
                bootCode.setType(NOP);
            } else if (bootCode.getType().equals(NOP)){
                bootCode.setType(JMP);
            }

            bootCodeList.set(i, bootCode);
            if ( !isBootingBroken(false)){
                return;
            }

            bootCode.setType(oldType);
        }
    }

    private boolean isBootingBroken(boolean displayBrokenMessage){

        int accumulator = 0;
        List<Integer> processedSeq = new ArrayList<>();

        for (int i = 0; i < bootCodeList.size(); i++) {

            if (processedSeq.contains(i)){
                if (displayBrokenMessage) {
                    System.out.println("Booting Instruction is interrupted. Accumulator is at: " + accumulator);
                }
                return true;
            }

            processedSeq.add(i);

            BootCode bootCode = bootCodeList.get(i);
            switch (bootCode.getType()){
                case ACC:
                    accumulator += bootCode.getValue();
                    break;
                case JMP:
                    i += bootCode.getValue() - 1;
                    break;
                default:
                    // nothing
            }
        }

        System.out.println("Booting is fixed and successfully terminated. Accumulator is at: " + accumulator);
        return false;
    }

    private class BootCode {
        private String type;
        private int value;

        public BootCode(String type, int value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public int getValue() {
            return value;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
