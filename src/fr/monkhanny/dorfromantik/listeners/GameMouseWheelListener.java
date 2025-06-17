package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.game.Board;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Listener pour gérer les événements de la molette de la souris dans le jeu.
 * Cette classe permet de faire pivoter la prochaine tuile sélectionnée sur le plateau
 * lorsque la molette de la souris est utilisée.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class GameMouseWheelListener implements MouseWheelListener {

    /**
     * Le plateau de jeu sur lequel les actions sont effectuées.
     */
    private Board board;
    
    /**
     * Constructeur pour initialiser le listener avec le plateau de jeu.
     *
     * @param board le plateau de jeu sur lequel effectuer les rotations de tuiles.
     */
    public GameMouseWheelListener(Board board) {
        this.board = board;
    }

    /**
     * Méthode appelée lorsqu'un événement de la molette de la souris est détecté.
     * Si une tuile suivante existe sur le plateau, cette méthode applique une rotation
     * à la tuile, dans le sens horaire ou antihoraire en fonction de la direction de la molette.
     * Après la rotation, le plateau est redessiné.
     *
     * @param e l'événement de la molette de la souris.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (board.getNextTile() != null) {
            boolean clockwise = e.getWheelRotation() < 0;  // Si la molette va vers le haut, rotation horaire
            board.getNextTile().rotate(clockwise);  // Applique la rotation sur la tuile
            board.repaint();
        }
    }
}
