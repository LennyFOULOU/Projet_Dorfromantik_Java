package fr.monkhanny.dorfromantik.controller;

import fr.monkhanny.dorfromantik.gui.MainMenu;
import fr.monkhanny.dorfromantik.gui.ButtonPanel;
import fr.monkhanny.dorfromantik.listeners.GameWindowCloseListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Contrôleur pour gérer les interactions avec les boutons du menu principal.
 * Ce contrôleur permet de gérer les actions liées aux boutons "Jouer", 
 * "Comment jouer ?", "Paramètres" et "Quitter" dans le menu principal.
 *
 * @version 1.0
 * @author Moncef STITI, Khalid CHENOUNA
 */
public class MainMenuButtonController implements ActionListener {

    /**
     * Instance du menu principal.
     */
    private final MainMenu mainMenu;

    /**
     * Fenêtre des paramètres.
     */
    private final JFrame settingsFrame;

    /**
     * Fenêtre d'aide (Comment jouer ?).
     */
    private final JFrame howToPlayFrame;

    /**
     * Fenêtre de sélection du mode de jeu.
     */
    private final JFrame gameModeFrame;

    /**
     * Fenêtre du jeu.
     */
    private final JFrame gameFrame;

    /**
     * Constructeur pour initialiser le contrôleur et attacher les actions aux boutons.
     *
     * @param mainMenu       Instance du menu principal
     * @param settingsFrame  Fenêtre des paramètres
     * @param howToPlayFrame Fenêtre d'aide (Comment jouer ?)
     * @param gameModeFrame  Fenêtre de sélection du mode de jeu
     * @param gameFrame      Fenêtre principale du jeu
     */
    public MainMenuButtonController(MainMenu mainMenu, JFrame settingsFrame, JFrame howToPlayFrame, JFrame gameModeFrame, JFrame gameFrame) {
        this.mainMenu = mainMenu;

        // Attacher les actions aux boutons du menu principal
        ButtonPanel buttonPanel = mainMenu.getButtonPanel();
        buttonPanel.getNewGameButton().addActionListener(this);
        buttonPanel.getHowToPlayButton().addActionListener(this);
        buttonPanel.getSettingsButton().addActionListener(this);
        buttonPanel.getExitButton().addActionListener(this);

        // Configurer les différentes fenêtres
        this.settingsFrame = settingsFrame;
        configureFrame(this.settingsFrame);

        this.howToPlayFrame = howToPlayFrame;
        configureFrame(this.howToPlayFrame);

        this.gameModeFrame = gameModeFrame;
        configureFrame(this.gameModeFrame);

        this.gameFrame = gameFrame;
        configureFrame(this.gameFrame);
        this.gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.gameFrame.addWindowListener(new GameWindowCloseListener(this.gameFrame));
    }

    /**
     * Configure une fenêtre (taille, position, comportement par défaut).
     *
     * @param frame La fenêtre à configurer
     */
    private void configureFrame(JFrame frame) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Gère les actions déclenchées par les boutons du menu principal.
     *
     * @param e Événement d'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Jouer":
                startNewGame();
                break;
            case "Comment jouer ?":
                showHowToPlay();
                break;
            case "Paramètres":
                openSettings();
                break;
            case "Quitter":
                exitGame();
                break;
            default:
                System.out.println("Commande inconnue: " + command);
                break;
        }
    }

    /**
     * Démarre une nouvelle partie en affichant la fenêtre de sélection du mode de jeu.
     */
    public void startNewGame() {
        adjustFrameDisplay(this.gameModeFrame);
        this.mainMenu.setVisible(false);
        this.gameModeFrame.setVisible(true);
    }

    /**
     * Affiche la fenêtre "Comment jouer ?".
     */
    public void showHowToPlay() {
        adjustFrameDisplay(this.howToPlayFrame);
        this.mainMenu.setVisible(false);
        this.howToPlayFrame.setVisible(true);
    }

    /**
     * Quitte le jeu en fermant l'application.
     */
    private void exitGame() {
        System.exit(0); // Ferme l'application
    }

    /**
     * Ouvre la fenêtre des paramètres.
     */
    private void openSettings() {
        adjustFrameDisplay(this.settingsFrame);
        this.mainMenu.setVisible(false);
        this.settingsFrame.setVisible(true);
    }

    /**
     * Ajuste la taille et la position d'une fenêtre par rapport au menu principal.
     *
     * @param frame La fenêtre à ajuster
     */
    private void adjustFrameDisplay(JFrame frame) {
        boolean wasVisible = frame.isVisible(); // Vérifie si la fenêtre est déjà visible

        if (!wasVisible) {
            // Ajuste la taille et la position de la fenêtre
            Dimension mainMenuSize = this.mainMenu.getSize();
            Point mainMenuLocation = this.mainMenu.getLocation();
            frame.setSize(mainMenuSize);
            frame.setLocation(mainMenuLocation);
        }
    }
}
