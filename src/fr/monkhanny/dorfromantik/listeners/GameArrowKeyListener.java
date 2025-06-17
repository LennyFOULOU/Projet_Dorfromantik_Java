package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.game.Board;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Listener pour gérer les touches fléchées permettant de déplacer le plateau de jeu.
 * Cette classe intercepte les événements de clavier pour déplacer le plateau dans les
 * directions haut, bas, gauche et droite en fonction des flèches appuyées.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class GameArrowKeyListener extends KeyAdapter {

    /**
     * Le plateau de jeu à manipuler lors des déplacements.
     */
    private Board board;

    /**
     * Constructeur pour initialiser le listener avec le plateau de jeu.
     *
     * @param board le plateau de jeu à déplacer en réponse aux événements de touches fléchées.
     */
    public GameArrowKeyListener(Board board) {
        this.board = board;
    }

    /**
     * Méthode appelée lorsqu'une touche est enfoncée.
     * Gère les déplacements du plateau selon la touche fléchée pressée.
     *
     * @param e l'événement de la touche pressée.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                board.moveBoard(0, -30); // Déplacer vers le haut
                break;
            case KeyEvent.VK_DOWN:
                board.moveBoard(0, 30); // Déplacer vers le bas
                break;
            case KeyEvent.VK_LEFT:
                board.moveBoard(-30, 0); // Déplacer vers la gauche
                break;
            case KeyEvent.VK_RIGHT:
                board.moveBoard(30, 0); // Déplacer vers la droite
                break;
        }
    }
}
