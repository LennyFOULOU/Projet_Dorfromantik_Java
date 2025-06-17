package fr.monkhanny.dorfromantik.gui;

import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.controller.ButtonHoverAnimationListener;

import javax.swing.*;
import java.awt.*;

/**
 * Classe gérant les animations de survol pour les boutons (JButton).
 * Permet d'effectuer des transitions douces sur les propriétés du bouton, telles que
 * la couleur du texte et la taille de la police, lors du survol ou du départ de la souris.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class ButtonHoverAnimator {

    /** Le bouton pour lequel les animations sont appliquées. */
    private final JButton button;

    /** La couleur d'origine du texte du bouton. */
    private final Color originalColor;

    /** La police d'origine du bouton, partagée pour toutes les instances. */
    private static Font originalFont;

    /** Timer utilisé pour exécuter les animations avec une transition fluide. */
    private Timer animationTimer;
       
    /**
     * Constructeur de l'animateur de bouton.
     * Initialise les propriétés d'origine (couleur, police) et associe l'animation au bouton spécifié.
     *
     * @param button Le bouton cible de l'animation.
     */
    public ButtonHoverAnimator(JButton button) {
        this.button = button;
        this.originalColor = button.getForeground();
        ButtonHoverAnimator.originalFont = button.getFont();
    }
    
    /**
     * Lance l'animation de transition sur le bouton, en fonction de l'état du survol.
     *
     * @param entering Indique si la souris entre ou quitte le bouton :
     *                 <ul>
     *                   <li><code>true</code> : la souris entre sur le bouton (animation d'entrée).</li>
     *                   <li><code>false</code> : la souris quitte le bouton (animation de sortie).</li>
     *                 </ul>
     */
    public void startAnimation(boolean entering) {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
    
        // Create a new ActionListener instance
        animationTimer = new Timer(Options.ANIMATION_DELAY, new ButtonHoverAnimationListener(entering, button, originalColor, originalFont));
        animationTimer.start();
    }
    
    /**
     * Met à jour la taille de la police originale partagée entre toutes les instances.
     * Cette méthode est utile pour adapter dynamiquement la police en fonction de
     * la taille de la fenêtre ou des préférences de l'utilisateur.
     *
     * @param newFontSize La nouvelle taille de la police.
     */
    public static void updateOriginalFont(float newFontSize) {
        originalFont = originalFont.deriveFont(newFontSize);
    }

}
