package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.gui.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener pour gérer le bouton de fermeture dans la fenêtre des paramètres.
 * Lors de l'action, le menu principal est réaffiché et la fenêtre des paramètres est cachée.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class CloseButtonListener implements ActionListener {

    /**
     * Le menu principal à réafficher lorsque le bouton est activé.
     */
    private MainMenu mainMenu;

    /**
     * La fenêtre des paramètres à fermer lorsque le bouton est activé.
     */
    private JFrame settingsFrame;

    /**
     * Constructeur pour initialiser le listener avec le menu principal et la fenêtre des paramètres.
     *
     * @param mainMenu      le menu principal à rendre visible.
     * @param settingsFrame la fenêtre des paramètres à cacher lors de l'action.
     */
    public CloseButtonListener(MainMenu mainMenu, JFrame settingsFrame) {
        this.mainMenu = mainMenu;
        this.settingsFrame = settingsFrame;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée sur le bouton de fermeture.
     * Rétablit la visibilité du menu principal et cache la fenêtre des paramètres.
     *
     * @param e l'événement d'action déclenché par le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Réafficher la fenêtre du menu principal
        mainMenu.setVisible(true);
        settingsFrame.setVisible(false);  // Fermer la fenêtre des paramètres
    }
}