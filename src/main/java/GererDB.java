package com.exemple;

import java.util.List;

public interface GererDB {
    void verify();
    void connect();
    void drop();
    void save(Data data);
    void create(); // Ajoutez cette méthode ici
    List<Data> getAllMoves(); // Ajoutez cette méthode si ce n'est pas déjà fait
}
