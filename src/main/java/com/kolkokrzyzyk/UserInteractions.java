package com.kolkokrzyzyk;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteractions extends Constants {


    Scanner scanner = new Scanner(System.in);

    public void newGame() {
        boolean exitGame = false;
        while (!exitGame) {
            switch (mainMenu()) {
                case MODE_3_BY_3:
                    String opponent3By3 = selectOpponent();
                    if (opponent3By3.equals(PLAYER_VS_PLAYER)) {
                        GameLogic thisGame = chooseMode(3, PLAYER_VS_PLAYER);
                        gameplay(thisGame, PLAYER_VS_PLAYER);
                        exitGame = endingMenu(thisGame, PLAYER_VS_PLAYER);
                    } else {
                        GameLogic thisGame = chooseMode(3, PLAYER_VS_COMPUTER);
                        gameplay(thisGame, PLAYER_VS_COMPUTER);
                        exitGame = endingMenu(thisGame, PLAYER_VS_COMPUTER);
                    }
                    break;
                case MODE_10_BY_10:
                    String opponent10By10 = selectOpponent();
                    if (opponent10By10.equals(PLAYER_VS_PLAYER)) {
                        GameLogic thisGame = chooseMode(10, PLAYER_VS_PLAYER);
                        gameplay(thisGame, PLAYER_VS_PLAYER);
                        exitGame = endingMenu(thisGame, PLAYER_VS_PLAYER);
                    } else {
                        GameLogic thisGame = chooseMode(10, PLAYER_VS_COMPUTER);
                        gameplay(thisGame, PLAYER_VS_COMPUTER);
                        exitGame = endingMenu(thisGame, PLAYER_VS_COMPUTER);
                    }
                    break;
                case EXIT:
                    System.out.println("Bye bye !");
                    exitGame = true;
                    break;
            }
        }
    }

    public String mainMenu() {
        while (true) {
            System.out.println();
            System.out.println("TIC TAC TOE GAME");
            System.out.println("Select game mode:");
            System.out.println("1 - 3 by 3");
            System.out.println("2 - 10 by 10");
            System.out.println("3 - Exit");
            String pickFromMenu = scanner.next();
            if (pickFromMenu.equals(MODE_3_BY_3) || pickFromMenu.equals(MODE_10_BY_10) || pickFromMenu.equals(EXIT)) {
                return pickFromMenu;
            } else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }
    }

    public Player player1SetUp() {
        System.out.println("Player 1 name : ");
        String player1name = scanner.next();
        if(player1name.equals("Computer")) {
            System.out.println("Och, so we will call You ComputerJunior.");
            return new Player("ComputerJunior" , PLAYER_1_SYMBOL);
        }
        return new Player(player1name, PLAYER_1_SYMBOL);
    }

    public Player player2SetUp() {
        System.out.println("Player 2 name : ");
        String player2name = scanner.next();
        return new Player(player2name, PLAYER_2_SYMBOL);
    }

    private Player computerPlayerSetUp() {
        return new Player("Computer", PLAYER_2_SYMBOL);
    }

    public GameLogic chooseMode(int boardSize, String selectOpponent) {
        if (selectOpponent.equals(PLAYER_VS_PLAYER)) {
            return new GameLogic(player1SetUp(), player2SetUp(), boardSize);
        }
        return new GameLogic(player1SetUp(), computerPlayerSetUp(), boardSize);

    }

    public String selectOpponent() {
        while (true) {
            System.out.println("1 - Player vs Player");
            System.out.println("2 - Player vs Computer");
            String menuSelectOpponent = scanner.next();
            switch (menuSelectOpponent) {
                case PLAYER_VS_PLAYER:
                    return PLAYER_VS_PLAYER;



                case PLAYER_VS_COMPUTER:
                    return PLAYER_VS_COMPUTER;


                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");

            }
        }
    }

    public void gameplay(GameLogic thisGame, String playerVS) {
        boolean gameOver = false;
        while(!gameOver) {
            boolean validMove = false;
            while(!validMove) {
                if(playerVS.equals(PLAYER_VS_COMPUTER) && thisGame.getCurrentPlayer().getName().equals("Computer")) {
                    System.out.println(thisGame.getCurrentPlayer() + " move!");
                    thisGame.makeComputerMove();
                    gameOver = checkGameOver(thisGame);
                    thisGame.switchPlayer();
                    break;
                }
                System.out.println(thisGame.getCurrentPlayer() + " - Your move!");
                try {
                    System.out.println("Row : ");
                    int row = scanner.nextInt();
                    System.out.println("Column");
                    int column = scanner.nextInt();
                    if (thisGame.makeMove(row, column, thisGame.getCurrentPlayer())) {
                        gameOver = checkGameOver(thisGame);
                        thisGame.switchPlayer();
                        validMove = true;
                        }
                } catch (InputMismatchException e) {
                        System.out.println("Error, only numbers from 1 to " + thisGame.boardLength);
                        scanner.next();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid move: row or column out of bounds.");
                    System.out.println("Please enter number from 1 to " + thisGame.boardLength);
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid move: field is occupied.");
                    scanner.nextLine();
                }
            }
        }
    }
    private boolean checkGameOver(GameLogic thisGame) {
        if(thisGame.checkDraw()) {
            System.out.println("Draw.");
            thisGame.showResult();
            return true;
        } else if (thisGame.checkWin()) {
            System.out.println("Congratulations !!!");
            System.out.println(thisGame.getCurrentPlayer() + " point for You!");
            thisGame.showResult();
            return true;
        } return false;
    }
    private boolean endingMenu(GameLogic game, String playerVs) {
        System.out.println("1 - Next game.");
        System.out.println("2 - Main menu.");
        System.out.println("3 - Exit.");
        String menuChoice = scanner.next();
        switch (menuChoice) {
            case NEXT_GAME:
                gameplay(game, playerVs);
                endingMenu(game, playerVs);
                break;
            case MAIN_MENU:
                break;
            case EXIT:
                System.out.println("Bye bye!");
                return true;

        } return false;
    }
}


