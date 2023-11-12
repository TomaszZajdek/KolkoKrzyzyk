package com.kolkokrzyzyk;

import java.awt.*;
import java.util.HashMap;

public class Board {

    public HashMap<Point, String> board;
    public HashMap<Point, String> getBoard() {
        return board;
    }
    int boardLength;
    public Board(int size) {
        boardLength = initializeClearBoard(size);
    }

    public int initializeClearBoard(int size) {
        board = new HashMap<>();
        for(int row = 1; row <= size; row++) {
            for(int column = 1; column <= size; column++) {
                board.put(new Point(row, column), " ");
            }
        } return size;
    }
    public void displayBoard() {
        System.out.println();
        for (int row = 1; row <= boardLength ; row++) {
            System.out.print("| ");
            for (int column = 1; column <= boardLength; column++) {
                System.out.print(board.get(new Point(row, column)) + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
