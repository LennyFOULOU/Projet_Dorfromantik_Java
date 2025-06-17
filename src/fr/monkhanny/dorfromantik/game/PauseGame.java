package fr.monkhanny.dorfromantik.game;

import fr.monkhanny.dorfromantik.Options;
import fr.monkhanny.dorfromantik.listeners.EscapeMenuCloseListener;
import fr.monkhanny.dorfromantik.listeners.GameQuitButtonListener;
import fr.monkhanny.dorfromantik.listeners.GameResumeButtonListener;
import fr.monkhanny.dorfromantik.listeners.GameSettingsButtonListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * Classe PauseGame, gérant la mise en pause du jeu via la touche Échap (ESC).
 * Elle affiche un menu d'échappement (EscapeMenu) contenant des options pour reprendre,
 * quitter ou accéder aux paramètres du jeu.
 * 
 * @version 1.0
 * @author Lenny FOULOU, Moncef STITI
 */

public class PauseGame extends KeyAdapter {
    private EscapeMenu escapeMenu;
    private GameResumeButtonListener resumeButtonListener;
    private GameQuitButtonListener quitButtonListener;
    private GameSettingsButtonListener settingsButtonListener;

    /**
     * Constructeur de la classe PauseGame.
     * 
     * @param gameFrame La fenêtre principale du jeu.
     * @param game L'instance de la classe Game associée.
     */

    public PauseGame(JFrame gameFrame, Game game) {
        // Initialiser escapeMenu ici avant de le passer à SettingsButtonListener
        this.escapeMenu = new EscapeMenu(gameFrame, game);  // Initialisation ici
        this.escapeMenu.setVisible(false);
        this.escapeMenu.setAlwaysOnTop(true);
        this.resumeButtonListener = new GameResumeButtonListener(this.escapeMenu);
        this.quitButtonListener = new GameQuitButtonListener();; // Initialisé après la création de escapeMenu
        this.settingsButtonListener = new GameSettingsButtonListener(gameFrame, this.escapeMenu); // Passer escapeMenu correctement
        this.escapeMenu.addWindowListener(new EscapeMenuCloseListener());
    }
    
    /**
     * Détecte les pressions de touches pour gérer la mise en pause du jeu.
     * Si la touche Échap (ESC) est pressée, le menu d'échappement est affiché.
     * 
     * @param e L'événement de touche pressée.
     */

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if(Options.isPaused == false){
                Options.isPaused = true;
                escapeMenu.setResumeButtonListener(this.resumeButtonListener);
                escapeMenu.setQuitButtonListener(this.quitButtonListener);
                escapeMenu.setSettingsButtonListener(this.settingsButtonListener);
                this.escapeMenu.setVisible(true);
            }
            escapeMenu.toFront();
        }
    }
}

