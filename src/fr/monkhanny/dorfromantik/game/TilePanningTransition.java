package fr.monkhanny.dorfromantik.game;

import fr.monkhanny.dorfromantik.listeners.TilePanningActionListener;

/**
 * Représente une transition de panoramique pour déplacer la vue du plateau de jeu.
 * 
 * Cette classe permet de déplacer la vue du plateau de jeu avec une animation fluide en
 * ajustant les décalages cibles sur les axes X et Y, répartis sur un nombre défini d'étapes.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class TilePanningTransition {

    /** Le plateau de jeu sur lequel le panoramique est appliqué. */
    private Board board;

    /** Décalage cible sur l'axe X, en pixels ou en unités logiques. */
    private int targetOffsetX;

    /** Décalage cible sur l'axe Y, en pixels ou en unités logiques. */
    private int targetOffsetY;

    /** Nombre d'étapes de l'animation pour atteindre la cible. */
    private int steps;

    /**
     * Initialise une transition de panoramique avec les paramètres spécifiés.
     * 
     * @param board Le plateau de jeu affecté par le panoramique.
     * @param targetOffsetX Décalage cible à atteindre sur l'axe X.
     * @param targetOffsetY Décalage cible à atteindre sur l'axe Y.
     * @param steps Nombre d'étapes de l'animation pour effectuer le panoramique.
     */
    public TilePanningTransition(Board board, int targetOffsetX, int targetOffsetY, int steps) {
        this.board = board;
        this.targetOffsetX = targetOffsetX;
        this.targetOffsetY = targetOffsetY;
        this.steps = steps;
    }

    /**
     * Démarre la transition de panoramique.
     * 
     * Cette méthode crée un écouteur d'animation (`TilePanningActionListener`) configuré avec
     * les cibles et les étapes définies, puis démarre l'animation si aucune n'est déjà en cours.
     */
    public void start() {
        // Créer un listener d'animation
        TilePanningActionListener listener = new TilePanningActionListener(board, targetOffsetX, targetOffsetY, steps);

        // Démarrer l'animation si aucune n'est en cours
        listener.startAnimation();
    }
}
