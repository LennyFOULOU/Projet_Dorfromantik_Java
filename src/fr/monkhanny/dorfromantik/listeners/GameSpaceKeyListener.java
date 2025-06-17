package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.game.Board;
/**
 * Listener pour gérer l'appui sur la touche Espace dans le jeu.
 * Lorsqu'un événement d'appui sur la touche Espace est détecté, le plateau de jeu est recentré grâce à une méthode spécifique.
 * 
 * 
 * @version 1.0
 * @author Lenny FOULOU
 */
public class GameSpaceKeyListener extends java.awt.event.KeyAdapter {
    /**
     * Le plateau de jeu à recentrer lorsque la touche Espace est enfoncée.
     */
    private Board board;
    /**
     * Constructeur pour initialiser le listener avec le plateau de jeu.
     *
     * @param board le plateau de jeu sur lequel appliquer l'action de la touche Espace.
     */
    public GameSpaceKeyListener(Board board) {
        this.board = board;
    }
   /**
     * Méthode appelée lorsqu'une touche du clavier est enfoncée.
     * Si la touche Espace est enfoncée, appelle une méthode du plateau de jeu 
     * pour le recentrer.
     *
     * @param e l'événement lié à l'appui sur une touche.
     */
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
            board.handleSpaceKeyPress();  // Appeler la méthode dans Board pour dézoomer
        }
    }
}
