package fr.monkhanny.dorfromantik.game;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
/**
 * Classe CustomMouseMotionAdapter, un écouteur personnalisé pour gérer les mouvements de souris
 * sur le plateau de jeu. Permet de transmettre les événements de mouvement de la souris au plateau.
 * @version 1.0
 * @author Lenny FOULOU, Khalid CHENOUNA
 */
public class CustomMouseMotionAdapter extends MouseMotionAdapter {
    private Board board;
    /**
     * Constructeur de CustomMouseMotionAdapter.
     *
     * @param board le plateau de jeu associé à cet écouteur
     */
    public CustomMouseMotionAdapter(Board board) {
        this.board = board;
    }
 /**
     * Gère les événements de déplacement de la souris.
     * Transmet l'événement de mouvement de la souris au plateau de jeu pour traitement.
     *
     * @param e l'événement MouseEvent représentant le mouvement de la souris
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        board.handleMouseMove(e);
    }
}
