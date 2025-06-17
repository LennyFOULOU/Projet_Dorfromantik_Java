package fr.monkhanny.dorfromantik.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import fr.monkhanny.dorfromantik.Options;


/**
 * Listener pour gérer la fermeture du menu d'échappement (pause).
 * Lorsque la fenêtre du menu d'échappement est fermée, ce listener met à jour l'état du jeu
 * pour indiquer qu'il n'est plus en pause.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class EscapeMenuCloseListener extends WindowAdapter {

    /**
     * Constructeur par défaut pour initialiser le listener.
     */
    public EscapeMenuCloseListener() {
        // Constructeur par défaut
    }

    /**
     * Méthode appelée lorsque la fenêtre du menu d'échappement est en cours de fermeture.
     * Met à jour l'état d'**Options.isPaused** pour indiquer que le jeu n'est plus en pause.
     *
     * @param e l'événement de fermeture de la fenêtre.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        Options.isPaused = false;
    }
}
