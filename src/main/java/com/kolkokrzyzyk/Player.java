package com.kolkokrzyzyk;

public class Player {


    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    private String name;
    private String symbol;
    private int wins;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        return getName();
    }
}
