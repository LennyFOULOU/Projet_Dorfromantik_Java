package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.game.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener pour animer le défilement du plateau.
 * Cette classe permet de déplacer progressivement le plateau en fonction de la cible et du nombre d'étapes.
 *
 * @version 1.0
 * @author Khalid Chenouna, Lenny FOULOU
 */
public class TilePanningActionListener implements ActionListener {
    /**
     * Référence au plateau.
     */
    private Board board;

    /**
     * Cible X de défilement du plateau.
     */
    private int targetOffsetX; 

    /**
     * Cible Y de défilement du plateau.
     */
    private int targetOffsetY;

    /**
     * Nombre d'étapes pour l'animation
     */
    private int steps;

    /**
     * Timer pour l'animation
     */
    private Timer timer;

    /**
     * Indique si une animation est en cours.
     */
    private static boolean isAnimating = false;

    /**
     * Compteur d'étapes pour l'animation.
     */
    private int currentStep = 0;

    /**
     * Constructeur pour initialiser les variables de l'animation.
     *
     * @param board         Référence au plateau
     * @param targetOffsetX Cible X de défilement du plateau
     * @param targetOffsetY Cible Y de défilement du plateau
     * @param steps         Nombre d'étapes pour l'animation
     */
    public TilePanningActionListener(Board board, int targetOffsetX, int targetOffsetY, int steps) {
        this.board = board;
        this.targetOffsetX = targetOffsetX;
        this.targetOffsetY = targetOffsetY;
        this.steps = steps;
    }

    /**
     * Méthode appelée à chaque étape de l'animation.
     *
     * @param e Evénement d'action
     * @see ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int currentOffsetX = board.getOffsetX();
        int currentOffsetY = board.getOffsetY();

        // Si l'animation a atteint la cible (exactement)
        if (currentStep >= steps) {
            board.setOffsetX(targetOffsetX);
            board.setOffsetY(targetOffsetY);
            stopAnimation();
        } else {
            // Calculer le delta pour chaque étape en utilisant des valeurs flottantes
            float deltaX = (float)(targetOffsetX - currentOffsetX) / (steps - currentStep);
            float deltaY = (float)(targetOffsetY - currentOffsetY) / (steps - currentStep);

            // Appliquer la transition progressivement avec un arrondi à l'entier le plus proche
            board.setOffsetX(currentOffsetX + Math.round(deltaX));
            board.setOffsetY(currentOffsetY + Math.round(deltaY));

            // Ne redessiner que si nécessaire
            if (currentStep % 2 == 0) {  // Par exemple, redessiner toutes les 2 étapes
                board.repaint();  // Re-dessiner le plateau
            }

            // Augmenter le compteur d'étapes
            currentStep++;
        }
    }

    /**
     * Démarrer l'animation.
     */
    public void startAnimation() {
        if (isAnimating) {
            stopAnimation();
        }

        isAnimating = true;

        // Réinitialiser les étapes
        currentStep = 0;

        // Créer et démarrer le timer pour l'animation
        this.timer = new Timer(1000 / 60, this);  // 60 FPS
        timer.start();
    }

    /**
     * Arrêter l'animation.
     */
    private void stopAnimation() {
        if (timer != null) {
            timer.stop();  // Arrêter le timer
        }
        isAnimating = false;  // Réinitialiser l'indicateur d'animation
    }
}
