package fr.monkhanny.dorfromantik.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Color;

/**
 * Listener pour animer un bouton lorsqu'il est survolé par la souris.
 * Cette classe permet de changer la couleur du bouton lorsque la souris entre ou sort de la zone du bouton.
 * 
 * @version 1.0
 * @author Moncef STITI, Lenny FOULOU
 */
public class TutorialButtonHoverListener extends MouseAdapter {
    /**
     * Référence au bouton à animer
     */
    private final JButton button;

    /**
     * Couleur de survol du bouton
     */
    private final Color hoverColor;

    /**
     * Couleur d'origine du bouton
     */
    private final Color originalColor;

    /**
     * Constructeur pour initialiser les variables du listener.
     *
     * @param button        Référence au bouton à animer
     * @param hoverColor    Couleur de survol du bouton
     * @param originalColor Couleur d'origine du bouton
     */
    public TutorialButtonHoverListener(JButton button, Color hoverColor, Color originalColor) {
        this.button = button;
        this.hoverColor = hoverColor;
        this.originalColor = originalColor;
    }

    /**
     * Change la couleur du bouton lorsque la souris entre dans la zone du bouton.
     * @param evt Evénement de souris
     * @see MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent evt) {
        button.setBackground(hoverColor); // Couleur plus claire au survol
    }

    /**
     * Change la couleur du bouton lorsque la souris sort de la zone du bouton.
     * @param evt Evénement de souris
     * @see MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent evt) {
        button.setBackground(originalColor); // Retour à la couleur originale
    }
}
