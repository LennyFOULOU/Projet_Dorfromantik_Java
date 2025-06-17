package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.game.Board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
/**
 * Listener pour gérer le zoom dans le jeu à l'aide de la molette de la souris.
 * Le zoom s'effectue uniquement lorsque la touche Ctrl est enfoncée.
 * 
 * @version 1.0
 * @author Lenny FOULOU
 */
public class GameZoomListener extends MouseAdapter {
     /**
     * Le plateau de jeu sur lequel les actions de zoom seront effectuées.
     */
    private Board board;
/**
     * Constructeur pour initialiser le listener avec le plateau de jeu.
     *
     * @param board le plateau de jeu sur lequel appliquer les zooms.
     */
    public GameZoomListener(Board board) {
        this.board = board;
    }
 /**
     * Méthode appelée lorsqu'un événement de molette de souris est détecté.
     * Si la touche Ctrl est enfoncée, effectue un zoom avant ou arrière en fonction de la rotation de la molette.
     *
     * @param e l'événement de molette de souris.
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // Vérifier si la touche Ctrl est enfoncée et la direction de la molette
        if (e.isControlDown()) {
            // Si la molette tourne vers le bas (zoom arrière)
            if (e.getWheelRotation() < 0) {
                board.zoomIn();  // Zoom avant
            } else {
                board.zoomOut();  // Zoom arrière
            }
        }
    }
}
