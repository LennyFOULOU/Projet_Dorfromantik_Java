package fr.monkhanny.dorfromantik.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.monkhanny.dorfromantik.gui.GameModeSelectionPanel;

/**
 * Listener pour gérer les clics sur le bouton "Suivant" dans le panneau de sélection du mode de jeu.
 * Cette classe permet de naviguer vers la page suivante des séries disponibles dans le panneau.
 * 
 * @version 1.0
 * @author Lenny FOULOU
 */
public class GameModeNextButtonActionListener implements ActionListener {

    /**
     * Le panneau de sélection du mode de jeu.
     */
    private GameModeSelectionPanel panel;

    /**
     * Constructeur pour initialiser le listener avec le panneau de sélection du mode de jeu.
     *
     * @param panel le panneau de sélection du mode de jeu sur lequel agir.
     */
    public GameModeNextButtonActionListener(GameModeSelectionPanel panel) {
        this.panel = panel;
    }
    
    /**
     * Méthode appelée lorsqu'une action est déclenchée, comme un clic sur le bouton "Suivant".
     * Elle passe à la page suivante et charge les séries associées à cette page.
     *
     * @param e l'événement de l'action déclenchée.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setCurrentPage(panel.getCurrentPage() + 1);
        panel.loadSeriesForCurrentPage();
    }
}
