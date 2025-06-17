package fr.monkhanny.dorfromantik;

import fr.monkhanny.dorfromantik.gui.MainMenu;
import fr.monkhanny.dorfromantik.controller.MainMenuResizeController;
import fr.monkhanny.dorfromantik.controller.MainMenuButtonController;
import fr.monkhanny.dorfromantik.utils.MusicPlayer;
import fr.monkhanny.dorfromantik.enums.Musics;
import fr.monkhanny.dorfromantik.listeners.CloseWindowListener;
import fr.monkhanny.dorfromantik.gui.SettingsPanel;
import fr.monkhanny.dorfromantik.controller.TutorialController;
import fr.monkhanny.dorfromantik.controller.GameModeController;
import fr.monkhanny.dorfromantik.gui.GameModeSelectionPanel;

import javax.swing.JFrame;

/**
 * Classe principale du jeu
 * 
 * @version 1.0
 * @author Moncef STITI
 * @see MainMenu
 * @see MainMenuResizeController
 */
public class Main {

    /**
     * Fenêtres de choix des modes de jeu
     */
    private static JFrame gameModeFrame;

    /**
     * Fenêtres de jeu
     */
    private static JFrame gameFrame;

    /**
     * Fenêtres des paramètres
     */
    private static JFrame settingsFrame;

    /**
     * Fenêtres du tutoriel
     */
    private static JFrame howToPlayFrame;

    /**
     * Indique si la musique a déjà été jouée
     */
    private static boolean isMusicPlayed = false;

    /**
     * Constructeur par défaut pour la classe Main.
     */
    public Main() {
        // Constructeur par défaut
    }

    /**
     * Réinitialiser le jeu
     */
    @SuppressWarnings("unused")
    public static void resetGame() {
        // 1. Fermer toutes les fenêtres ouvertes
        if (gameModeFrame != null) {
            gameModeFrame.dispose();  // Ferme la fenêtre du choix des modes de jeu
        }
        if (gameFrame != null) {
            gameFrame.dispose();  // Ferme la fenêtre de jeu
        }
        if (settingsFrame != null) {
            settingsFrame.dispose();  // Ferme la fenêtre des paramètres
        }
        if (howToPlayFrame != null) {
            howToPlayFrame.dispose();  // Ferme la fenêtre du tutoriel
        }

        // 2. Réinitialiser les variables globales ou statiques si nécessaire
        Options.mainMenu = new MainMenu();  // Réinitialiser le menu principal

        // 3. Lancer la musique uniquement si ce n'est pas déjà fait
        if (!isMusicPlayed) {
            MusicPlayer.loadMusic(Musics.MAIN_MENU_MUSIC);  // Recharger la musique du menu principal
            MusicPlayer.playMusic();  // Reprendre la musique
            isMusicPlayed = true;  // Marquer la musique comme jouée
        }


        // 4. Créer les fenêtres à nouveau comme au début
        gameModeFrame = new JFrame("Choix des séries - Dorfromantik");
        gameModeFrame.setMinimumSize(Options.MINIMUM_FRAME_SIZE);
        gameFrame = new JFrame("Jeu - Dorfromantik");
        gameFrame.setMinimumSize(Options.MINIMUM_FRAME_SIZE);
        settingsFrame = new JFrame("Paramètres - Dorfromantik");
        settingsFrame.setMinimumSize(Options.MINIMUM_FRAME_SIZE);
        howToPlayFrame = new JFrame("Comment jouer ? - Dorfromantik");
        howToPlayFrame.setMinimumSize(Options.MINIMUM_FRAME_SIZE);
        
        // Re-créer et réinitialiser les panels et les contrôleurs
        MainMenuResizeController mainMenuResizeController = new MainMenuResizeController(Options.mainMenu);
        MainMenuButtonController mainMenuButtonController = new MainMenuButtonController(Options.mainMenu, settingsFrame, howToPlayFrame, gameModeFrame, gameFrame);

        // Fenêtre des paramètres
        CloseWindowListener settingsWindowListener = new CloseWindowListener(Options.mainMenu, settingsFrame);
        SettingsPanel settingsPanel = new SettingsPanel(Options.mainMenu, settingsFrame);
        settingsFrame.addWindowListener(settingsWindowListener);
        settingsFrame.add(settingsPanel);

        // Fenêtre du tutoriel
        CloseWindowListener howToPlayWindowListener = new CloseWindowListener(Options.mainMenu, howToPlayFrame);
        TutorialController tutorialController = new TutorialController(Options.mainMenu, howToPlayFrame);
        howToPlayFrame.addWindowListener(howToPlayWindowListener);
        howToPlayFrame.add(tutorialController.getTutorialPanel());

        // Fenêtre du choix des modes de jeu
        CloseWindowListener gameModeWindowListener = new CloseWindowListener(Options.mainMenu, gameModeFrame);
        GameModeController gameModeController = new GameModeController(gameFrame, Options.mainMenu, gameModeFrame);
        GameModeSelectionPanel gameModeSelectionPanel = new GameModeSelectionPanel(gameModeController,gameModeFrame, Options.mainMenu);
        gameModeFrame.addWindowListener(gameModeWindowListener);
        gameModeController.setGameModeSelectionPanel(gameModeSelectionPanel);
        gameModeFrame.add(gameModeSelectionPanel);

        // Afficher à nouveau le menu principal
        Options.mainMenu.setVisible(true);
    }

    /**
     * Méthode principale du jeu
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        // Appel initial pour créer les fenêtres et démarrer le jeu
        resetGame();  // Appel à la fonction de réinitialisation
    }
}
