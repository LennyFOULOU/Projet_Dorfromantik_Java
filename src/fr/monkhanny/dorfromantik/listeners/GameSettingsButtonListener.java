package fr.monkhanny.dorfromantik.listeners;

import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.game.EscapeMenu;
import fr.monkhanny.dorfromantik.gui.SettingsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * Listener pour gérer l'ouverture de la fenêtre des paramètres lorsqu'un bouton est cliqué.
 * Ce listener cache le menu d'échappement (escape menu), affiche une nouvelle fenêtre avec les paramètres, et configure cette fenêtre pour revenir à la fenêtre principale du jeu lorsqu'elle est fermée.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class GameSettingsButtonListener implements ActionListener {
    /**
     * La fenêtre principale du jeu.
     */
    private JFrame gameFrame;

    /**
     * La fenêtre dédiée aux paramètres.
     */
    private JFrame settingsFrame;

    /**
     * Le panneau contenant les options de configuration.
     */
    private SettingsPanel settingsPanel;

    /**
     * Le menu d'échappement (menu pause) qui doit être caché lorsque les paramètres sont affichés.
     */
    private EscapeMenu escapeMenu;

    /**
     * Constructeur pour initialiser le listener avec la fenêtre principale et le menu d'échappement.
     *
     * @param gameFrame  la fenêtre principale du jeu.
     * @param escapeMenu le menu d'échappement (menu pause) à cacher lorsque la fenêtre des paramètres s'affiche.
     */
    public GameSettingsButtonListener(JFrame gameFrame, EscapeMenu escapeMenu) {
        this.gameFrame = gameFrame;
        this.escapeMenu = escapeMenu;
        this.settingsFrame = new JFrame("Paramètres - Dorfromantik");
        this.settingsPanel = new SettingsPanel(Options.mainMenu, settingsFrame);
        this.settingsPanel.setReturnButtonVisible(false); // On cache le bouton de retour au menu principal

        // Ajouter le WindowListener pour réafficher la gameFrame lors de la fermeture de settingsFrame
        this.settingsFrame.addWindowListener(new GameSettingsWindowListener(gameFrame));
    }

    /**
     * Méthode appelée lorsqu'une action est déclenchée, comme un clic sur un bouton.
     * Elle ouvre la fenêtre des paramètres, cache la fenêtre du jeu et configure
     * la fenêtre des paramètres avec la taille et la position de la fenêtre principale.
     *
     * @param e l'événement de l'action déclenchée.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (escapeMenu != null) {  // Vérifier si escapeMenu est non nul
            escapeMenu.setVisible(false);
        }

        // Obtenir la taille et la position de la gameFrame
        int width = gameFrame.getWidth() - 50;
        int height = gameFrame.getHeight() - 50;
        int x = gameFrame.getX();
        int y = gameFrame.getY();

        // Appliquer ces dimensions et position à settingsFrame
        settingsFrame.setSize(width, height);
        settingsFrame.setLocation(x, y);

        // Ajouter le panneau des paramètres si ce n'est pas déjà fait
        settingsFrame.add(this.settingsPanel);

        // Cacher la gameFrame et afficher settingsFrame
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setVisible(true);
        Options.isPaused = false;
    }
}
