package fr.monkhanny.dorfromantik.listeners;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Listener pour gérer les événements liés à la fenêtre des paramètres du jeu.
 * Ce listener réaffiche la fenêtre principale du jeu (gameFrame) lorsque la fenêtre des paramètres est en train de se fermer.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class GameSettingsWindowListener implements WindowListener {
    /**
     * La fenêtre du jeu.
     */
    private JFrame gameFrame;

    /**
     * Constructeur pour initialiser le listener avec la fenêtre principale du jeu.
     *
     * @param gameFrame la fenêtre principale du jeu.
     */
    public GameSettingsWindowListener(JFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    /**
     * Méthode appelée lorsque la fenêtre est ouverte.
     * Aucun comportement n'est implémenté pour cet événement.
     *
     * @param e l'événement de fenêtre.
     */
    @Override
    public void windowOpened(WindowEvent e) {
        // Rien à faire ici
    }

    /**
     * Méthode appelée lorsque la fenêtre est en train de se fermer.
     * Cette implémentation réaffiche la fenêtre principale du jeu.
     *
     * @param e l'événement de fenêtre.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        // Réafficher la gameFrame
        gameFrame.setVisible(true);
    }

    /**
     * Méthode appelée lorsque la fenêtre a été fermée.
     * Aucun comportement n'est implémenté pour cet événement.
     *
     * @param e l'événement de fenêtre.
     */
    @Override
    public void windowClosed(WindowEvent e) {
        // Rien à faire ici
    }

    /**
     * Méthode appelée lorsque la fenêtre est réduite en icône.
     * Aucun comportement n'est implémenté pour cet événement.
     *
     * @param e l'événement de fenêtre.
     */
    @Override
    public void windowIconified(WindowEvent e) {
        // Rien à faire ici
    }

    /**
     * Méthode appelée lorsque la fenêtre est restaurée après avoir été réduite.
     * Aucun comportement n'est implémenté pour cet événement.
     *
     * @param e l'événement de fenêtre.
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
        // Rien à faire ici
    }

    /**
     * Méthode appelée lorsque la fenêtre devient active.
     * Aucun comportement n'est implémenté pour cet événement.
     *
     * @param e l'événement de fenêtre.
     */
    @Override
    public void windowActivated(WindowEvent e) {
        // Rien à faire ici
    }

    /**
     * Méthode appelée lorsque la fenêtre devient inactive.
     * Aucun comportement n'est implémenté pour cet événement.
     *
     * @param e l'événement de fenêtre.
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
        // Rien à faire ici
    }
}