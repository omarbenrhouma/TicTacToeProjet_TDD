package com.exemple;

import java.util.ArrayList;
import java.util.List;

public class RealGererDB implements GererDB {
    private final List<Data> database = new ArrayList<>();

    @Override
    public void verify() {
        System.out.println("Real: Verifying database...");
    }

    @Override
    public void connect() {
        System.out.println("Real: Connecting to database...");
    }

    @Override
    public void drop() {
        System.out.println("Real: Dropping all moves...");
        database.clear(); // Réinitialise les données
    }

    @Override
    public void create() {
        System.out.println("Real: Creating database...");
        // Simule la création de la base si elle n'existe pas
    }

    @Override
    public void save(Data data) {
        System.out.println("Real: Saving data -> " + data);
        database.add(data); // Ajoute le mouvement dans la base simulée
    }

    @Override
    public List<Data> getAllMoves() {
        return new ArrayList<>(database); // Renvoie une copie de la liste
    }
}
