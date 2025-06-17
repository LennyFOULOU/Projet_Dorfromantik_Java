package fr.monkhanny.dorfromantik.controller;

import fr.monkhanny.dorfromantik.gui.GameModeSelectionPanel;
import fr.monkhanny.dorfromantik.gui.MainMenu;
import fr.monkhanny.dorfromantik.game.Board;
import fr.monkhanny.dorfromantik.utils.Database;
import fr.monkhanny.dorfromantik.Options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.awt.Dimension;
import java.awt.Point;

/**
 * Contrôleur pour gérer les interactions liées à la sélection du mode de jeu.
 * Cette classe gère les actions déclenchées depuis l'interface de sélection de mode de jeu,
 * comme le démarrage d'une partie ou la récupération de seeds depuis une base de données.
 *
 * @version 1.0
 * @author Moncef STITI, Khalid CHENOUNA
 */
public class GameModeController implements ActionListener {

    /**
     * Panneau de sélection du mode de jeu.
     */
    private GameModeSelectionPanel gameModeSelectionPanel;

    /**
     * Fenêtre principale du jeu.
     */
    private JFrame gameFrame;

    /**
     * Menu principal de l'application.
     */
    private MainMenu mainMenu;

    /**
     * Fenêtre de sélection du mode de jeu.
     */
    private JFrame gameModeFrame;

    /**
     * Instance de la base de données pour gérer les seeds.
     */
    private Database database;

    /**
     * Instance du plateau de jeu actif.
     */
    private static Board board;

    /**
     * Constructeur pour initialiser le contrôleur sans panneau de sélection.
     *
     * @param gameFrame     Fenêtre principale du jeu
     * @param mainMenu      Menu principal
     * @param gameModeFrame Fenêtre de sélection du mode de jeu
     */
    public GameModeController(JFrame gameFrame, MainMenu mainMenu, JFrame gameModeFrame) {
        this.gameFrame = gameFrame;
        this.mainMenu = mainMenu;
        this.gameModeFrame = gameModeFrame;

        // Connexion à la base de données
        try {
            this.database = new Database();
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à la base de données: " + e.getMessage());
        }
    }

    /**
     * Associe le panneau de sélection du mode de jeu à ce contrôleur.
     *
     * @param panel Panneau de sélection du mode de jeu
     */
    public void setGameModeSelectionPanel(GameModeSelectionPanel panel) {
        this.gameModeSelectionPanel = panel;
    }

    /**
     * Gère les actions déclenchées par des événements utilisateur, comme le choix ou le démarrage d'un mode de jeu.
     *
     * @param e Événement d'action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (isDynamicSeries(command)) {
            startGame(getSeedFromDatabaseByName(command));
        } else if (command.equals("Démarrer")) {
            long seed = gameModeSelectionPanel.getLongSeed();
            startGame(seed);
            addCustomSeedToDatabase(seed);
        } else {
            System.out.println("Commande inconnue: " + command);
        }
    }

    /**
     * Récupère une seed depuis la base de données en fonction du nom du mode de jeu.
     *
     * @param modeName Nom du mode de jeu
     * @return La seed associée au mode de jeu, ou -1 en cas d'erreur
     */
    private long getSeedFromDatabaseByName(String modeName) {
        try {
            Options.SEED = this.database.getSeedByName(modeName);
            return Options.SEED;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Ajoute une seed personnalisée dans la base de données.
     *
     * @param seed La seed à ajouter
     */
    private void addCustomSeedToDatabase(long seed) {
        try {
            Options.SEED = seed;
            this.database.addCustomSeed(seed);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'ajout de la seed custom.");
        }
    }

    /**
     * Vérifie si une série est dynamique en vérifiant son existence dans la base de données.
     *
     * @param seriesName Nom de la série
     * @return {@code true} si la série est dynamique, {@code false} sinon
     */
    private boolean isDynamicSeries(String seriesName) {
        try {
            return database.getSeedByName(seriesName) != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Démarre une partie avec une seed spécifique.
     *
     * @param seed La seed utilisée pour démarrer le jeu
     */
    private void startGame(long seed) {
        GameModeController.board = null;
        this.gameModeFrame.setVisible(false);
        this.gameFrame.setVisible(false);

        if (Options.FULL_SCREEN) {
            gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            Dimension mainMenuSize = this.mainMenu.getSize();
            Point mainMenuLocation = this.mainMenu.getLocation();
            gameFrame.setSize(mainMenuSize);
            gameFrame.setLocation(mainMenuLocation);
        }

        GameModeController.board = new Board(this.gameFrame, seed);
        this.gameFrame.setVisible(true);
        this.gameFrame.add(board);
    }

    /**
     * Récupère le plateau de jeu actif.
     *
     * @return Le plateau de jeu actif
     */
    public static Board getGameModeBoard() {
        return board;
    }
}
