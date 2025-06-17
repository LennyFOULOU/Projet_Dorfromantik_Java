package fr.monkhanny.dorfromantik.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe CustomKeyAdapter, un écouteur personnalisé pour gérer les entrées clavier sur le plateau de jeu.
 * Permet de réagir aux touches pressées pour effectuer des actions spécifiques dans le jeu.
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class CustomKeyAdapter extends KeyAdapter {
    private Board board;
  /**
     * Constructeur de CustomKeyAdapter.
     *
     @param board le plateau de jeu associé à cet écouteur
  */
    public CustomKeyAdapter(Board board) {
        this.board = board;
    }
    /**
     * Gère les événements de touche pressée.
     * Si la touche 'T' est pressée, cette méthode bascule la visibilité du menu de contrôle.
     *
     * @param e l'événement KeyEvent représentant la touche pressée
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_T) { // Touche 'T' pour cacher/montrer le menu
            board.getControlsMenu().toggleVisibility();
        }
    }
}
