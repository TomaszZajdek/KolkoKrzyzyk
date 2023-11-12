package com.kolkokrzyzyk;

import java.awt.*;

public class GameLogic extends Board{

    private Player player1;
    private Player player2;
    private Player currentPlayer;
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public GameLogic(Player player1, Player player2, int boardSize) {
        super(boardSize);
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = firstTurn();
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void switchPlayer () {
        if(getCurrentPlayer().equals(player1)) {
            setCurrentPlayer(player2);
        } else {
            setCurrentPlayer(player1);
        }
    }
    private Player firstTurn() {
        int num = (int) (Math.random() * 2);
        if(num == 0) {
            return player1;
        }
        return player2;
    }
    public boolean makeMove(int row, int column, Player player) {
        if (checkIfMoveIsValid(row, column)) {
            board.put(new Point(row, column), player.getSymbol());
            displayBoard();
            return true;

        } return false;
    }
    public boolean checkIfMoveIsValid(int row, int column) {
        if (row < 1 || row > boardLength || column < 1 || column > boardLength) {
            throw new IndexOutOfBoundsException("Invalid move: row or column out of bounds.");
        }

        if (board.get(new Point(row, column)).equals(" ")) {
            return true;
        } else {
            throw new IllegalArgumentException("Invalid move: field is occupied.");
        }
    }
    public boolean checkDraw() {
        boolean draw = true;
        for(int row = 1; row <= boardLength; row++) {
            for(int column = 1; column <= boardLength; column++) {
                if(board.get(new Point(row, column)).equals(" ")) {
                    draw = false;
                }
            }
        }
        if(draw) {
            initializeClearBoard(boardLength);
        }
        return draw;
    }
    public boolean checkWin() {
        int consecutiveSymbolsToWin = 0;
        if(boardLength <= 3) {
            consecutiveSymbolsToWin = 3;
        } else {
            consecutiveSymbolsToWin = 5;
        }
        String playerSymbol = currentPlayer.getSymbol();
        // checking rows
        for(int row = 1; row <= boardLength; row++) {
            int consecutiveSymbols = 0;
            for (int column = 1; column <= boardLength; column++) {
                if(board.get(new Point(row, column)).equals(playerSymbol)) {
                    consecutiveSymbols++;
                    if(consecutiveSymbols >= consecutiveSymbolsToWin) {
                        addWinStatistic();
                        initializeClearBoard(boardLength);
                        return true;
                    }
                } else {
                    consecutiveSymbols = 0;
                }
            }
        }
        // checking columns
        for(int column = 1; column <= boardLength; column++) {
            int consecutiveSymbols = 0;
            for (int row = 1; row <= boardLength; row++) {
                if(board.get(new Point(row, column)).equals(playerSymbol)) {
                    consecutiveSymbols++;
                    if(consecutiveSymbols >= consecutiveSymbolsToWin) {
                        addWinStatistic();
                        initializeClearBoard(boardLength);
                        return true;
                    }
                } else {
                    consecutiveSymbols = 0;
                }
            }
        }
        // checking diagonals from top left to bottom right
        for(int row = 1; row <= boardLength; row++) {
            int consecutiveSymbols = 0;
            int column = 1;
            for(int low = row; low <= boardLength; low++ ) {
                if (board.get(new Point(low, column)).equals(playerSymbol)) {
                    consecutiveSymbols++;
                    if (consecutiveSymbols >= consecutiveSymbolsToWin) {
                        addWinStatistic();
                        initializeClearBoard(boardLength);
                        return true;
                    }
                } else {
                    consecutiveSymbols = 0;
                }
                column++;
            }
        }
        // checking diagonals from top right to bottom left
        for(int row = 1; row <= boardLength; row++) {
            int consecutiveSymbols = 0;
            int column = boardLength;
            for(int low = row; low <= boardLength; low++ ) {
                if (board.get(new Point(low, column)).equals(playerSymbol)) {
                    consecutiveSymbols++;
                    if (consecutiveSymbols >= consecutiveSymbolsToWin) {
                        addWinStatistic();
                        initializeClearBoard(boardLength);
                        return true;
                    }
                } else {
                    consecutiveSymbols = 0;
                }
                column--;
            }
        }
        return false;
    }
    public void makeComputerMove() {
        boolean computerMove = false;
        while (!computerMove) {
            int row = (int) (Math.random() * boardLength) + 1;
            int column = (int) (Math.random() * boardLength) + 1;
            if (checkIfMoveIsValid(row, column)) {
                makeMove(row, column, currentPlayer);
                computerMove = true;
            }
        }
    }
    public void addWinStatistic() {
        if(currentPlayer.getName().equals(player1.getName())) {
            player1.setWins(player1.getWins() + 1);
        } else if (currentPlayer.getName().equals(player2.getName())){
            player2.setWins(player2.getWins() + 1);
        }
    }
    public void showResult() {
        System.out.println(player1.getName() + "     " + player1.getWins()
                + "  :  " + player2.getWins() + "     " + player2.getName()  );
    }


}
