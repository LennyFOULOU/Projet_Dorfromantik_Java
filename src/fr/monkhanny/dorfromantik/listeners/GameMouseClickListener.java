package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.game.Board;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listener pour gérer les événements de clics de souris dans le jeu.
 * Cette classe permet d'appeler la méthode appropriée du plateau de jeu
 * lorsqu'un clic de souris est effectué.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class GameMouseClickListener extends MouseAdapter {

    /**
     * Le plateau de jeu sur lequel les clics de souris sont traités.
     */
    private Board board;

    /**
     * Constructeur pour initialiser le listener avec le plateau de jeu.
     *
     * @param board le plateau de jeu sur lequel gérer les clics de souris.
     */
    public GameMouseClickListener(Board board) {
        this.board = board;
    }

    /**
     * Méthode appelée lorsqu'un clic de souris est détecté sur le plateau de jeu.
     * Elle délègue le traitement du clic à la méthode {@code handleMouseClick} du plateau.
     *
     * @param e l'événement du clic de souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        board.handleMouseClick(e);
    }
}
