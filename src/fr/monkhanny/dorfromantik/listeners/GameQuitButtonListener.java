package fr.monkhanny.dorfromantik.listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pour gérer la fermeture du jeu lorsque le bouton "Quitter" est cliqué.
 * Cette classe termine l'exécution de l'application.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class GameQuitButtonListener implements ActionListener {
    
    /**
     * Constructeur par défaut pour initialiser le listener.
     */
    public GameQuitButtonListener() {

    }

    /**
     * Méthode appelée lorsqu'une action est déclenchée, comme un clic sur le bouton "Quitter".
     * Cette implémentation termine immédiatement l'exécution de l'application.
     *
     * @param e l'événement de l'action déclenchée.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Quitter
        System.exit(0);
    }
}
