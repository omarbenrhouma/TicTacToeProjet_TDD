package com.exemple;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeSaveTest {

    @Test
    void shouldSaveMoveSuccessfully() {
        GererDB dbManager = mock(GererDB.class);
        TicTacToeSave ticTacToeSave = new TicTacToeSave(dbManager);
        Data move = new Data(1, 0, 0, 'X');

        try {
            doNothing().when(dbManager).verify();
            doNothing().when(dbManager).connect();
            doNothing().when(dbManager).save(move);
        } catch (Exception e) {
            fail("Unexpected exception in mock setup");
        }

        assertTrue(ticTacToeSave.saveMove(move), "The move should be saved successfully");
    }

    @Test
    void shouldCreateDatabaseAndSaveMoveSuccessfully() {
        // Mock de GererDB
        GererDB mockDB = mock(GererDB.class);

        // Configuration des mocks
        try {
            doNothing().when(mockDB).verify();   // Simule que la base de données existe
            doNothing().when(mockDB).connect(); // Simule une connexion réussie
            doNothing().when(mockDB).save(any(Data.class)); // Simule une sauvegarde réussie
        } catch (Exception e) {
            fail("Mock setup failed: " + e.getMessage());
        }

        // Instance de TicTacToeSave avec le mock
        TicTacToeSave save = new TicTacToeSave(mockDB);

        // Création d'un mouvement
        Data move = new Data(1, 0, 0, 'X');

        // Vérification : La sauvegarde doit réussir
        assertTrue(save.saveMove(move), "The move should be saved after creating the database");
    }
    @Test
    void shouldCallCreateWhenDatabaseDoesNotExist() {
        // Mock de GererDB
        GererDB mockDB = mock(GererDB.class);

        try {
            // Simuler une exception lors de verify()
            doThrow(new RuntimeException("Database not found")).when(mockDB).verify();
            doNothing().when(mockDB).create(); // Simule la création réussie de la base
            doNothing().when(mockDB).connect(); // Simule une connexion réussie
            doNothing().when(mockDB).save(any(Data.class)); // Simule une sauvegarde réussie
        } catch (Exception e) {
            fail("Mock setup failed: " + e.getMessage());
        }

        // Instance de TicTacToeSave avec le mock
        TicTacToeSave save = new TicTacToeSave(mockDB);

        // Création d'un mouvement
        Data move = new Data(1, 0, 0, 'X');

        // Appeler saveMove et vérifier qu'il retourne true
        assertTrue(save.saveMove(move), "The move should be saved after creating the database");

        // Vérifier que create() a été appelé une fois
        try {
            verify(mockDB, times(1)).create();
        } catch (Exception e) {
            fail("Verification of create() call failed: " + e.getMessage());
        }

        // Vérifier que save() a été appelé une fois
        try {
            verify(mockDB, times(1)).save(any(Data.class));
        } catch (Exception e) {
            fail("Verification of save() call failed: " + e.getMessage());
        }
    }

    @Test
    void shouldFailToSaveMoveWhenDatabaseConnectionFails() {
        GererDB dbManager = mock(GererDB.class);
        TicTacToeSave ticTacToeSave = new TicTacToeSave(dbManager);
        Data move = new Data(1, 0, 0, 'X');

        try {
            doNothing().when(dbManager).verify();
            doThrow(new RuntimeException("Connection failed")).when(dbManager).connect();
        } catch (Exception e) {
            fail("Unexpected exception in mock setup");
        }

        assertFalse(ticTacToeSave.saveMove(move), "The move should not be saved due to connection failure");
    }

    @Test
    void shouldInitializeGameSuccessfully() {
        GererDB dbManager = mock(GererDB.class);
        TicTacToeSave ticTacToeSave = new TicTacToeSave(dbManager);

        try {
            doNothing().when(dbManager).verify();
            doNothing().when(dbManager).connect();
            doNothing().when(dbManager).drop();
        } catch (Exception e) {
            fail("Unexpected exception in mock setup");
        }

        assertTrue(ticTacToeSave.initializeGame(), "The game should be initialized successfully");
    }

    @Test
    void shouldFailToInitializeGameWhenDropFails() {
        GererDB dbManager = mock(GererDB.class);
        TicTacToeSave ticTacToeSave = new TicTacToeSave(dbManager);

        try {
            doNothing().when(dbManager).verify();
            doNothing().when(dbManager).connect();
            doThrow(new RuntimeException("Drop failed")).when(dbManager).drop();
        } catch (Exception e) {
            fail("Unexpected exception in mock setup");
        }

        assertFalse(ticTacToeSave.initializeGame(), "The game should not be initialized due to a drop failure");
    }
}
