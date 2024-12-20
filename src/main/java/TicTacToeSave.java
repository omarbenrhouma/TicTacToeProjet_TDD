package com.exemple;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeSave {
    private final GererDB dbManager;

    public TicTacToeSave(GererDB dbManager) {
        this.dbManager = dbManager;
    }

    public boolean saveMove(Data move) {
        try {
            dbManager.verify();
            dbManager.connect();
            dbManager.save(move);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
            return false;
        }
    }

    public List<Data> getAllMoves() {
        try {
            dbManager.verify();
            dbManager.connect();
            return dbManager.getAllMoves();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des mouvements : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean initializeGame() {
        try {
            dbManager.verify();
            dbManager.drop();
            dbManager.create();
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors de l'initialisation du jeu : " + e.getMessage());
            return false;
        }
    }
}
