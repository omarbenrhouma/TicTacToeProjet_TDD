package com.exemple;

public class Data {
    private final int turn;
    private final int x;
    private final int y;
    private final char player;

    public Data(int turn, int x, int y, char player) {
        this.turn = turn;
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getTurn() {
        return turn;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Turn: " + turn + ", Player: " + player + ", Position: (" + x + ", " + y + ")";
    }
}
