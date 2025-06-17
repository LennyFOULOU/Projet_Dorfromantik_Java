package fr.monkhanny.dorfromantik.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.monkhanny.dorfromantik.gui.GameModeSelectionPanel;


/**
 * Listener pour gérer les clics sur le bouton de filtrage dans le panneau de sélection du mode de jeu.
 * Cette classe permet de réinitialiser la page de sélection à la première page lors de l'application d'un filtre
 * et de charger les séries correspondantes à cette page.
 * 
 * @version 1.0
 * @author Lenny FOULOU
 */
public class GameModeFilterButtonActionListener implements ActionListener {

    /**
     * Le panneau de sélection du mode de jeu.
     */
    private GameModeSelectionPanel panel;

    /**
     * Constructeur pour initialiser le listener avec le panneau de sélection du mode de jeu.
     *
     * @param panel le panneau de sélection du mode de jeu sur lequel agir.
     */
    public GameModeFilterButtonActionListener(GameModeSelectionPanel panel) {
        this.panel = panel;
    }

    /**
     * Méthode appelée lorsqu'une action est déclenchée, comme un clic sur le bouton de filtrage.
     * Elle réinitialise la page actuelle à la première page et recharge les séries correspondant à cette page.
     *
     * @param e l'événement de l'action déclenchée.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setCurrentPage(1); // Reset to first page when filtering
        panel.loadSeriesForCurrentPage();
    }
}
