package com.exemple;

import java.util.Scanner;

public class Board {
    private final char[][] grid = new char[3][3];
    private char currentPlayer = 'X';
    private final TicTacToeSave ticTacToeSave;

    public Board(TicTacToeSave ticTacToeSave) {
        this.ticTacToeSave = ticTacToeSave;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void placePiece(int x, int y, char player) {
        if (player != currentPlayer) {
            throw new IllegalArgumentException("It's not " + player + "'s turn to play");
        }
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new OutOfBoundsException("Position (" + x + ", " + y + ") is out of bounds");
        }
        if (grid[x][y] != '\0') {
            throw new SpaceOccupiedException("Position (" + x + ", " + y + ") is already occupied");
        }
        grid[x][y] = player;

        // Sauvegarder le mouvement dans la base de données
        Data move = new Data(getCurrentTurn(), x, y, player);
        if (!ticTacToeSave.saveMove(move)) {
            System.out.println("Failed to save the move.");
        }

        switchPlayer();
    }

    public boolean initializeGame() {
        if (ticTacToeSave.initializeGame()) {
            System.out.println("Game initialized successfully.");
            return true;
        } else {
            System.out.println("Failed to initialize the game.");
            return false;
        }
    }

    public int getCurrentTurn() {
        int turnCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != '\0') {
                    turnCount++;
                }
            }
        }
        return turnCount + 1;
    }

    public Character checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != '\0' && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                return grid[i][0];
            }
        }
        for (int j = 0; j < 3; j++) {
            if (grid[0][j] != '\0' && grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j]) {
                return grid[0][j];
            }
        }
        if (grid[0][0] != '\0' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return grid[0][0];
        }
        if (grid[0][2] != '\0' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return grid[0][2];
        }
        return null;
    }

    public boolean checkDraw() {
        if (checkWinner() == null) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == '\0') {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public void printGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] == '\0' ? "-" : grid[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
    }
}
