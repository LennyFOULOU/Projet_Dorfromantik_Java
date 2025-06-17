package fr.monkhanny.dorfromantik.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.game.EscapeMenu;

/**
 * Listener pour gérer la reprise du jeu lorsque le bouton "Reprendre" est cliqué dans le menu d'échappement.
 * Cette classe permet de masquer le menu de pause et de remettre le jeu dans un état actif.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class GameResumeButtonListener implements ActionListener {
    /**
     * Le menu d'échappement (menu pause) qui doit être masqué lorsque le jeu reprend.
     */
    private EscapeMenu escapeMenu;

    /**
     * Constructeur pour initialiser le listener avec le menu d'échappement.
     *
     * @param escapeMenu le menu d'échappement à masquer.
     */
    public GameResumeButtonListener(EscapeMenu escapeMenu) {
        this.escapeMenu = escapeMenu;
    }
    
    /**
     * Méthode appelée lorsqu'une action est déclenchée, comme un clic sur le bouton "Reprendre".
     * Elle masque le menu d'échappement et remet le jeu en état actif en désactivant le mode pause.
     *
     * @param e l'événement de l'action déclenchée.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Cacher le menu de pause
        escapeMenu.setVisible(false);
        Options.isPaused = false; // Mettre à jour l'état du jeu pour qu'il ne soit plus en pause
    }
}
