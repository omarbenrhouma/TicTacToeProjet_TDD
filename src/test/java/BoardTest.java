package com.exemple;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void shouldDeclareDrawWhenBoardIsFullAndNoWinner() {
        // Mock de TicTacToeSave
        TicTacToeSave mockSave = mock(TicTacToeSave.class);
        when(mockSave.saveMove(any(Data.class))).thenReturn(true);

        // Création de l'instance de Board avec le mock
        Board board = new Board(mockSave);

        // Remplir la grille sans qu'il y ait de gagnant
        board.placePiece(0, 0, board.getCurrentPlayer()); // X
        board.placePiece(0, 1, board.getCurrentPlayer()); // O
        board.placePiece(0, 2, board.getCurrentPlayer()); // X
        board.placePiece(1, 1, board.getCurrentPlayer()); // O
        board.placePiece(1, 0, board.getCurrentPlayer()); // X
        board.placePiece(1, 2, board.getCurrentPlayer()); // O
        board.placePiece(2, 1, board.getCurrentPlayer()); // X
        board.placePiece(2, 0, board.getCurrentPlayer()); // O
        board.placePiece(2, 2, board.getCurrentPlayer()); // X

        // Vérification du match nul
        assertTrue(board.checkDraw(), "The game should be a draw when the board is full and no winner");
    }

    @Test
    void shouldDeclareWinnerWhenRowIsComplete() {
        TicTacToeSave mockSave = mock(TicTacToeSave.class);
        when(mockSave.saveMove(any(Data.class))).thenReturn(true);

        Board board = new Board(mockSave);

        // Placer des pièces pour compléter une ligne
        board.placePiece(0, 0, board.getCurrentPlayer()); // X joue
        board.placePiece(1, 0, board.getCurrentPlayer()); // O joue
        board.placePiece(0, 1, board.getCurrentPlayer()); // X joue
        board.placePiece(1, 1, board.getCurrentPlayer()); // O joue
        board.placePiece(0, 2, board.getCurrentPlayer()); // X termine la ligne

        // Vérification du gagnant
        assertEquals('X', board.checkWinner(), "Player X should win with a complete row");
    }

    @Test
    void shouldDeclareWinnerWhenColumnIsComplete() {
        TicTacToeSave mockSave = mock(TicTacToeSave.class);
        when(mockSave.saveMove(any(Data.class))).thenReturn(true);

        Board board = new Board(mockSave);

        // Placer des pièces pour compléter une colonne
        board.placePiece(0, 0, board.getCurrentPlayer()); // X joue
        board.placePiece(0, 1, board.getCurrentPlayer()); // O joue
        board.placePiece(1, 0, board.getCurrentPlayer()); // X joue
        board.placePiece(1, 1, board.getCurrentPlayer()); // O joue
        board.placePiece(2, 0, board.getCurrentPlayer()); // X termine la colonne

        // Vérification du gagnant
        assertEquals('X', board.checkWinner(), "Player X should win with a complete column");
    }

    @Test
    void shouldDeclareWinnerWhenDiagonalIsComplete() {
        TicTacToeSave mockSave = mock(TicTacToeSave.class);
        when(mockSave.saveMove(any(Data.class))).thenReturn(true);

        Board board = new Board(mockSave);

        // Placer des pièces pour compléter une diagonale
        board.placePiece(0, 0, board.getCurrentPlayer()); // X joue
        board.placePiece(0, 1, board.getCurrentPlayer()); // O joue
        board.placePiece(1, 1, board.getCurrentPlayer()); // X joue
        board.placePiece(1, 2, board.getCurrentPlayer()); // O joue
        board.placePiece(2, 2, board.getCurrentPlayer()); // X termine la diagonale

        // Vérification du gagnant
        assertEquals('X', board.checkWinner(), "Player X should win with a complete diagonal");
    }

    @Test
    void shouldThrowOutOfBoundsExceptionWhenPieceIsPlacedOutsideXAxis() {
        TicTacToeSave mockSave = mock(TicTacToeSave.class);
        when(mockSave.saveMove(any(Data.class))).thenReturn(true);

        Board board = new Board(mockSave);

        // Vérifier les exceptions pour des coordonnées hors des limites sur l'axe X
        assertThrows(OutOfBoundsException.class,
                () -> board.placePiece(-1, 0, board.getCurrentPlayer()),
                "Expected OutOfBoundsException when x = -1");
        assertThrows(OutOfBoundsException.class,
                () -> board.placePiece(3, 0, board.getCurrentPlayer()),
                "Expected OutOfBoundsException when x = 3");
    }

    @Test
    void shouldThrowSpaceOccupiedExceptionWhenPieceIsPlacedOnOccupiedSpace() {
        TicTacToeSave mockSave = mock(TicTacToeSave.class);
        when(mockSave.saveMove(any(Data.class))).thenReturn(true);

        Board board = new Board(mockSave);

        // Placer une pièce et vérifier l'exception si on essaie de placer au même endroit
        board.placePiece(0, 0, board.getCurrentPlayer()); // X joue
        assertThrows(SpaceOccupiedException.class,
                () -> board.placePiece(0, 0, board.getCurrentPlayer()),
                "Expected SpaceOccupiedException when placing on an occupied space");
    }
}
