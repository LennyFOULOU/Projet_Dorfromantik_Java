package fr.monkhanny.dorfromantik.game;

import java.util.Random;

/**
 * Classe Game, représentant un objet de jeu qui gère les fonctionnalités générales.
 * Fournit des outils aléatoires pour générer des événements ou des valeurs dans le jeu.
 * 
 * @version 1.0
 * @author Lenny FOULOU, Khalid CHENOUNA
 */
public class Game {
    private Random random; // Générateur de nombres aléatoires

    /**
     * Constructeur de la classe Game avec un seed spécifique.
     * Permet de contrôler le générateur aléatoire pour des résultats reproductibles.
     * 
     * @param seed La graine utilisée pour initialiser le générateur aléatoire.
     */
    public Game(long seed) {
        this.random = new Random(seed);
    }

    /**
     * Constructeur par défaut de la classe Game.
     * Initialise un générateur aléatoire sans graine pour des résultats non reproductibles.
     */
    public Game() {
        this.random = new Random();
    }

    /**
     * Génère un entier aléatoire compris entre 0 (inclus) et max (exclus).
     * 
     * @param max La limite supérieure (exclusive) pour les nombres générés.
     * @return Un entier aléatoire compris entre 0 (inclus) et max (exclus).
     */
    public int getRandomInt(int max) {
        return random.nextInt(max);
    }
}
