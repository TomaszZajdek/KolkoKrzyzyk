package com.kolkokrzyzyk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KKTestSuite {
    @Test
    void checkIfMoveIsValidInvalidMoveOutOfBounds() {
        // Given
        int boardSize = 3;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);

        // Then
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            nGame.checkIfMoveIsValid(0, 4);
        });
    }

    @Test
    void checkIfMoveIsValidInvalidMoveOccupiedField() {
        // Given
        int boardSize = 3;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);

        // When
        nGame.makeMove(1, 1, p1);

        // Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            nGame.checkIfMoveIsValid(1, 1);
        });
    }

    @Test
    void checkDrawTest() {
        //Given
        int boardSize = 3;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);
        //when
        nGame.makeMove(1,1, p1);
        nGame.makeMove(1,3, p1);
        nGame.makeMove(2,2, p1);
        nGame.makeMove(3,2, p1);
        nGame.makeMove(1,2, p2);
        nGame.makeMove(2,1, p2);
        nGame.makeMove(2,3, p2);
        nGame.makeMove(3,3, p2);
        nGame.makeMove(3,1, p2);
        boolean draw = nGame.checkDraw();
        //Then
        Assertions.assertTrue(draw);
        Assertions.assertFalse(nGame.checkWin());


    }
    @Test
    void checkWinORowsConditionTest() {
        //Given
        int boardSize = 10;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);
        nGame.setCurrentPlayer(p2);
        //when
        nGame.makeMove(1, 1, p2);
        nGame.makeMove(1, 2, p2);
        nGame.makeMove(1, 3, p2);
        nGame.makeMove(1, 4, p2);
        nGame.makeMove(1, 5, p2);
        boolean win = nGame.checkWin();
        //Then
        Assertions.assertEquals(p2.getWins(), 1);
        Assertions.assertEquals(p1.getWins(), 0);
        Assertions.assertFalse(nGame.checkDraw());
        Assertions.assertTrue(win);

    }
    @Test
    void checkWinOColumnsConditionTest() {
        //Given
        int boardSize = 10;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);
        nGame.setCurrentPlayer(p2);
        //when
        nGame.makeMove(1, 1, p2);
        nGame.makeMove(2, 1, p2);
        nGame.makeMove(3, 1, p2);
        nGame.makeMove(4, 1, p2);
        nGame.makeMove(5, 1, p2);
        boolean win = nGame.checkWin();
        //Then
        Assertions.assertEquals(p2.getWins(), 1);
        Assertions.assertEquals(p1.getWins(), 0);
        Assertions.assertFalse(nGame.checkDraw());
        Assertions.assertTrue(win);

    }
    @Test
    void checkWinODiagonalConditionTest() {
        //Given
        int boardSize = 10;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);
        nGame.setCurrentPlayer(p2);
        //when
        nGame.makeMove(1, 1, p2);
        nGame.makeMove(2, 2, p2);
        nGame.makeMove(3, 3, p2);
        nGame.makeMove(4, 4, p2);
        nGame.makeMove(5, 5, p2);
        boolean win = nGame.checkWin();
        //Then
        Assertions.assertEquals(p2.getWins(), 1);
        Assertions.assertEquals(p1.getWins(), 0);
        Assertions.assertFalse(nGame.checkDraw());
        Assertions.assertTrue(win);

    }
    @Test
    void checkWinXRowsConditionTest() {
        //Given
        int boardSize = 10;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);
        nGame.setCurrentPlayer(p1);
        //when
        nGame.makeMove(1, 1, p1);
        nGame.makeMove(1, 2, p1);
        nGame.makeMove(1, 3, p1);
        nGame.makeMove(1, 4, p1);
        nGame.makeMove(1, 5, p1);
        boolean win = nGame.checkWin();
        //Then
        Assertions.assertEquals(p2.getWins(), 0);
        Assertions.assertEquals(p1.getWins(), 1);
        Assertions.assertFalse(nGame.checkDraw());
        Assertions.assertTrue(win);

    }
    @Test
    void checkWinXColumnsConditionTest() {
        //Given
        int boardSize = 10;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);
        nGame.setCurrentPlayer(p1);
        //when
        nGame.makeMove(1, 1, p1);
        nGame.makeMove(2, 1, p1);
        nGame.makeMove(3, 1, p1);
        nGame.makeMove(4, 1, p1);
        nGame.makeMove(5, 1, p1);
        boolean win = nGame.checkWin();
        //Then
        Assertions.assertEquals(0, p2.getWins());
        Assertions.assertEquals(p1.getWins(), 1);
        Assertions.assertFalse(nGame.checkDraw());
        Assertions.assertTrue(win);

    }
    @Test
    void checkWinXDiagonalConditionTest() {
        //Given
        int boardSize = 10;
        String player2Symbol = "O";
        String player1Symbol = "X";

        Player p1 = new Player("Jan", player1Symbol);
        Player p2 = new Player("Tadeusz", player2Symbol);
        GameLogic nGame = new GameLogic(p1, p2, boardSize);
        nGame.setCurrentPlayer(p1);
        //when
        nGame.makeMove(1, 1, p1);
        nGame.makeMove(2, 2, p1);
        nGame.makeMove(3, 3, p1);
        nGame.makeMove(4, 4, p1);
        nGame.makeMove(5, 5, p1);
        boolean win = nGame.checkWin();
        //Then
        Assertions.assertEquals(p2.getWins(), 0);
        Assertions.assertEquals(p1.getWins(), 1);
        Assertions.assertFalse(nGame.checkDraw());
        Assertions.assertTrue(win);

    }
}

