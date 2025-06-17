package fr.monkhanny.dorfromantik.controller;

import fr.monkhanny.dorfromantik.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pour animer un bouton lorsqu'il est survolé par la souris.
 * Cette classe permet d'agrandir ou de réduire la taille de la police et de changer la couleur du bouton
 * lorsque la souris entre ou sort de la zone du bouton.
 * 
 * @version 1.0
 * @author Moncef STITI, Lenny FOULOU
 */
public class ButtonHoverAnimationListener implements ActionListener {

    /*
     * Compteur d'étapes pour l'animation
     */
    private int step;

    /*
     * Incrément de mise à l'échelle à chaque étape
     */
    private final float scaleIncrement;

    /*
     * Indique si la souris entre ou quitte le bouton
     */
    private final boolean entering;

    /*
     * Référence au bouton à animer
     */
    private final JButton button;

    /*
     * Couleur d'origine du bouton
     */
    private final Color originalColor;

    /*
     * Police d'origine du bouton
     */
    private final Font originalFont;

    /*
     * Échelle actuelle de la police
     */
    private float currentScale = 1.0f;

    /**
     * Constructeur pour initialiser les variables du listener.
     *
     * @param entering      Indique si la souris entre ou quitte le bouton
     * @param button        Référence au bouton à animer
     * @param originalColor Couleur d'origine du bouton
     * @param originalFont  Police d'origine du bouton
     */
    public ButtonHoverAnimationListener(boolean entering, JButton button, Color originalColor, Font originalFont) {
        this.step = 0;
        this.entering = entering;
        this.button = button;
        this.originalColor = originalColor;
        this.originalFont = originalFont;
        this.scaleIncrement = (Options.HOVER_FONT_SCALE - 1.0f) / Options.ANIMATION_STEPS;
    }

    /**
     * Méthode appelée à chaque étape de l'animation.
     * Modifie la taille de la police et la couleur du bouton en fonction de l'état de l'animation.
     * 
     * @param e l'événement d'action qui est émis par le Timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Calcul de la nouvelle échelle en fonction de l'état "entrant" ou "sortant"
        currentScale = entering ? (1.0f + step * scaleIncrement) : (Options.HOVER_FONT_SCALE - step * scaleIncrement);

        // Modification de la couleur du bouton en fonction de l'état "entrant" ou "sortant"
        button.setForeground(entering ? Options.BUTTON_HOVER_COLOR : originalColor);
        // Application de la nouvelle taille de police au bouton
        button.setFont(originalFont.deriveFont(originalFont.getSize2D() * currentScale));

        // Incrémente le compteur d'étapes
        step++;
        // Si le nombre d'étapes est atteint, arrête le Timer et réinitialise les éléments si la souris sort
        if (step >= Options.ANIMATION_STEPS) {
            ((Timer) e.getSource()).stop();
            if (!entering) {
                button.setFont(originalFont); // Restauration de la police originale si la souris quitte le bouton
            }
        }
    }
}
