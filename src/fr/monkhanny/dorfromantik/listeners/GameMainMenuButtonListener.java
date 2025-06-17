package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * Listener pour gérer le clic sur le bouton de retour au menu principal dans le jeu.
 * Cette classe cache la fenêtre actuelle du jeu et réinitialise l'état du jeu en appelant la méthode `Main.resetGame`.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class GameMainMenuButtonListener implements ActionListener {

    /**
     * La fenêtre principale du jeu à cacher lors du retour au menu principal.
     */
    private JFrame gameFrame;

    /**
     * Constructeur pour initialiser le listener avec la fenêtre du jeu.
     *
     * @param gameFrame la fenêtre principale du jeu à manipuler.
     */
    public GameMainMenuButtonListener(JFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    /**
     * Méthode appelée lorsqu'une action est déclenchée, comme un clic sur le bouton du menu principal.
     * Cette méthode cache la fenêtre du jeu et appelle la méthode statique pour réinitialiser le jeu.
     *
     * @param e l'événement de l'action déclenchée.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameFrame.setVisible(false);
        Main.resetGame();
    }
}