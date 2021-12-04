package com.wengkee.adventofcode.y2021.day4;

import com.wengkee.adventofcode.util.Challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bingo extends Challenge {

    private List<String> drawNumbersOrder;
    private List<Board> boardList;

    public Bingo(int year, int day, int part, String input) {
        super(year, day, part, input);
    }

    @Override
    public void run() {
        if ( getPart() == 1){
            getWinner();
        }

        if ( getPart() == 2){
            getLoser();
        }
    }

    private void getWinner(){

        loadBingoDrawingSequence();
        loadBoards();

        for (int i = 0; i < drawNumbersOrder.size(); i++) {

            String numberBeingDrawn = drawNumbersOrder.get(i);

            for (Board board : boardList){
                board.markBoard(numberBeingDrawn);
            }

            boolean isWon = false;
            if (i >= 4){
                for (Board board : boardList){
                    if (board.isWon()){
                        System.out.println("This board has won! Seq: " + i  + ", num was: " + numberBeingDrawn);
                        int unmarkedSum = board.findUnmarkedSum();
                        System.out.println("Answer is: " + (unmarkedSum * Integer.parseInt(numberBeingDrawn)) );
                        isWon = true;
                    }
                }
            }

            if (isWon) break;
        }
    }

    private void getLoser(){

        loadBingoDrawingSequence();
        loadBoards();

        int lastWinningSeq = 0;
        int lastWinningNum = 0;
        Board lastWinningBoard = new Board();

        for (Board board : boardList){
            for (int i = 0; i < drawNumbersOrder.size(); i++) {
                String numberBeingDrawn = drawNumbersOrder.get(i);
                board.markBoard(numberBeingDrawn);
                if (i >= 4){
                    if (board.isWon()){
                        if ( lastWinningSeq == 0 || i > lastWinningSeq){
                            lastWinningSeq = i;
                            lastWinningBoard = board;
                            lastWinningNum = Integer.parseInt(numberBeingDrawn);
                        }
                        break;
                    }
                }
            }
        }

        int unmarkedSum = lastWinningBoard.findUnmarkedSum();
        lastWinningBoard.printBoard();
        System.out.println("This board is a loser, at seq: " + lastWinningSeq +", lastWinningNum: " + lastWinningNum);
        System.out.println("Answer is: " + (unmarkedSum * lastWinningNum) );

    }

    private void loadBingoDrawingSequence(){
        this.drawNumbersOrder = new ArrayList<>();
        this.drawNumbersOrder = Arrays.asList(getInputData().get(0).split(","));
    }

    private void loadBoards(){

        boardList = new ArrayList<>();

        // already knew
        // row 0 is drawing sequence
        // row 1 is empty line
        int rowNum = 2;
        while (rowNum < getInputData().size()){
            Board board = new Board(rowNum);
            boardList.add(board);
            rowNum += 6;
        }
        System.out.println("There are " + boardList.size() + " boards. ");
    }

    private class Board{
        private BingoNumber[][] board;

        public Board(){
            this.board = new BingoNumber[5][5];
        }

        public Board(int rowStartingNum){
            this();
            setBoard(rowStartingNum);
        }

        public int findUnmarkedSum(){
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    BingoNumber num = board[i][j];
                    if (!num.isMarked()){
                        sum += Integer.parseInt(num.getNum());
                    }
                }
            }
            return sum;
        }

        private void setBoard(int dataStartingRow){

            for (int rowNum = dataStartingRow; rowNum < dataStartingRow+5; rowNum++) {

                String data = getInputData().get(rowNum).trim().replaceAll("\\s+", "#");
                String[] columns = data.split("#");

                for (int columnNum = 0; columnNum < columns.length; columnNum++) {
                    board[rowNum-dataStartingRow][columnNum] = new BingoNumber(columns[columnNum]);
                }
            }
        }

        public void printBoard(){
            System.out.println("**********************");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.printf("%s  ", board[i][j].getNum());
                }
                System.out.println();
            }
            System.out.println("**********************");
        }

//        private int processAndGetWinSeq(){
//            int winningSeq = 0;
//            for (int i = 0; i < drawNumbersOrder.size(); i++) {
//                markBoard(drawNumbersOrder.get(i));
//                if (i >= 5 && isWon() ){
//                    winningSeq = i;
//                    System.out.println("Game won at seq: " + i +", the number was: " + drawNumbersOrder.get(i));
//                    break;
//                }
//            }
//            return winningSeq;
//        }

        private void markBoard(String drewNum){
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    BingoNumber currBingoNum = board[i][j];
                    if (drewNum.equals(currBingoNum.getNum())){
                        currBingoNum.setMarked();
                        break;
                    }
                }
            }
        }

        private boolean isWon(){

            // process horizontal
            for (int i = 0; i < 5; i++) {
                int winningRowCount = 0, winningColCount = 0;
                boolean breakRow = false, breakCol = false;
                for (int j = 0; j < 5; j++) {

                    // processing row and col at the same time
                    BingoNumber numOnIJ = board[i][j];
                    BingoNumber numOnJI = board[j][i];

                    if (numOnIJ == null || numOnIJ.getNum().length() == 0){
                        breakRow = true;
                    }

                    if (numOnJI == null || numOnJI.getNum().length() == 0){
                        breakCol = true;
                    }

                    if (breakRow && breakCol){
                        break;
                    }

                    if ( !breakRow && numOnIJ.isMarked() ){
                        winningRowCount++;
                        if (winningRowCount == 5){
                            return true;
                        }
                    }

                    if ( !breakCol && numOnJI.isMarked()){
                        winningColCount++;
                        if (winningColCount == 5){
                            return true;
                        }
                    }
                }
            }

            return false;
        }

        private class BingoNumber{
            private String num;
            private Boolean marked;

            public BingoNumber(String num){
                this.num = num;
                this.marked = false;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public Boolean isMarked() {
                return marked;
            }

            public void setMarked() {
                this.marked = true;
            }
        }
    }

}
