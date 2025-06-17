package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.gui.MainMenu;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


/**
 * Listener pour gérer la fermeture d'une fenêtre secondaire et réafficher le menu principal.
 * Lorsque la fenêtre surveillée est fermée, ce listener rend visible le menu principal et cache la fenêtre en cours.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class CloseWindowListener extends WindowAdapter {

    /**
     * Le menu principal à réafficher lorsque la fenêtre est fermée.
     */
    private MainMenu mainMenu;

    /**
     * La fenêtre à cacher lors de sa fermeture.
     */
    private JFrame frame;

    /**
     * Constructeur pour initialiser le listener avec le menu principal et la fenêtre surveillée.
     *
     * @param mainMenu le menu principal à rendre visible.
     * @param frame    la fenêtre surveillée à cacher lors de sa fermeture.
     */
    public CloseWindowListener(MainMenu mainMenu, JFrame frame) {
        this.mainMenu = mainMenu;
        this.frame = frame;
    }

    /**
     * Méthode appelée lorsque la fenêtre est en cours de fermeture.
     * Rétablit la visibilité du menu principal et cache la fenêtre surveillée.
     *
     * @param e l'événement de fermeture de la fenêtre.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        // Réafficher la fenêtre du menu principal
        mainMenu.setVisible(true);
        frame.setVisible(false);
    }
}
