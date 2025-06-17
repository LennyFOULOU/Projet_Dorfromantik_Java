package fr.monkhanny.dorfromantik.controller;

import fr.monkhanny.dorfromantik.gui.ButtonHoverAnimator;
import fr.monkhanny.dorfromantik.utils.MusicPlayer;
import fr.monkhanny.dorfromantik.enums.Sounds;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listener pour gérer les interactions de survol avec un bouton.
 * Ce listener déclenche une animation et un effet sonore lorsqu'un bouton est survolé ou quitté par la souris.
 *
 * @version 1.0
 * @author Moncef STITI, Lenny FOULOU
 */
public class ButtonHoverListener extends MouseAdapter {

    /**
     * Gestionnaire d'animation pour le survol du bouton.
     */
    private final ButtonHoverAnimator animator;

    /**
     * Constructeur pour initialiser le listener avec un animateur.
     *
     * @param animator Instance de {@link ButtonHoverAnimator} pour gérer l'animation du bouton
     */
    public ButtonHoverListener(ButtonHoverAnimator animator) {
        this.animator = animator;
    }

    /**
     * Déclenché lorsque la souris entre dans la zone du bouton.
     * Démarre l'animation de survol et joue un son.
     *
     * @param e Événement de souris
     * @see MouseEvent
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        animator.startAnimation(true);          // Démarre l'animation de survol
        MusicPlayer.loadSound(Sounds.SOUNDS1); // Charge le son
        MusicPlayer.playSound();               // Joue le son
    }

    /**
     * Déclenché lorsque la souris quitte la zone du bouton.
     * Arrête l'animation de survol.
     *
     * @param e Événement de souris
     * @see MouseEvent
     */
    @Override
    public void mouseExited(MouseEvent e) {
        animator.startAnimation(false); // Arrête l'animation de survol
    }
}
