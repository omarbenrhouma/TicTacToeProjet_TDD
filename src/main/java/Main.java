package com.exemple;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Utiliser un mock ou une base de données réelle
        GererDB dbManager = new RealGererDB();
        TicTacToeSave ticTacToeSave = new TicTacToeSave(dbManager);

        Board board = new Board(ticTacToeSave);

        System.out.println("Initialisation du jeu...");
        if (board.initializeGame()) {
            System.out.println("Jeu initialisé avec succès.");
            board.printGrid(); // Affiche la grille initiale
        } else {
            System.err.println("Échec de l'initialisation du jeu.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Afficher le joueur actuel
                System.out.println("Joueur actuel : " + board.getCurrentPlayer());
                System.out.print("Entrez votre mouvement (ligne colonne) : ");

                int x = scanner.nextInt();
                int y = scanner.nextInt();

                // Placer le mouvement sur la grille
                board.placePiece(x, y, board.getCurrentPlayer());
                board.printGrid(); // Affiche la grille après chaque mouvement

                // Afficher l'historique des mouvements
                List<Data> history = ticTacToeSave.getAllMoves();
                System.out.println("Historique des mouvements :");
                for (Data d : history) {
                    System.out.println("Tour: " + d.getTurn() + ", Joueur: " + d.getPlayer() + ", Position: (" + d.getX() + ", " + d.getY() + ")");
                }

                // Vérifier si un joueur a gagné
                Character winner = board.checkWinner();
                if (winner != null) {
                    System.out.println("Le joueur " + winner + " a gagné !");
                    break;
                }

                // Vérifier si c'est un match nul
                if (board.checkDraw()) {
                    System.out.println("C'est un match nul !");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                scanner.nextLine(); // Pour éviter une boucle infinie en cas d'erreur d'entrée
            }
        }

        scanner.close();
    }
}
