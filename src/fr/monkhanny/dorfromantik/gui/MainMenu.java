package fr.monkhanny.dorfromantik.gui;

import fr.monkhanny.dorfromantik.utils.FontManager;
import fr.monkhanny.dorfromantik.utils.ImageLoader;
import fr.monkhanny.dorfromantik.enums.Fonts;
import fr.monkhanny.dorfromantik.listeners.GameWindowCloseListener;
import fr.monkhanny.dorfromantik.components.Title;
import fr.monkhanny.dorfromantik.Options;

import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant le menu principal du jeu.
 * Elle gère l'affichage du titre, des boutons et du leaderboard.
 * @version 1.0
 * @author Lenny FOULOU
 */
public class MainMenu extends JFrame {

    /**
     * Titre du menu principal, affiché en haut au centre de la fenêtre.
     */
    private Title titleLabel;

    /**
     * Panneau contenant les boutons du menu principal.
     */
    private ButtonPanel buttonPanel;

    /**
     * Conteneur pour le leaderboard.
     */
    private JPanel leaderboardContainer;

    /**
     * Référence au leaderboard actuel affiché.
     */
    private Leaderboard currentLeaderboard;

    /**
     * Constructeur pour initialiser le menu principal.
     * Charge les polices, configure la fenêtre principale et ajoute les éléments graphiques
     * tels que le titre, les boutons et le leaderboard.
     */
    public MainMenu() {
        // Charger les polices pour le titre et les boutons
        FontManager.loadCustomFont(Fonts.TITLE);  // Charge la police pour le titre
        FontManager.loadCustomFont(Fonts.BUTTON); // Charge la police pour les boutons

        // Paramétrage de la fenêtre principale
        this.setTitle("Dorfromantik - Menu Principal");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new GameWindowCloseListener(this));
        super.setIconImage(ImageLoader.APPLICATION_ICON);
        this.setMinimumSize(Options.MINIMUM_FRAME_SIZE);
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null); // Centrer la fenêtre
        this.setLayout(new BorderLayout());

        // Arrière plan du menu principal
        JLabel background = new JLabel(new ImageIcon("./ressources/images/MainMenu/background.jpg"));
        background.setLayout(new BorderLayout());
        this.setContentPane(background);

        // Ajouter le titre en haut au centre
        this.titleLabel = new Title("Dorfromantik", Options.BASE_TITLE_FONT_SIZE);
        background.add(titleLabel, BorderLayout.NORTH);

        // Panneau des boutons avec style personnalisé (à gauche)
        this.buttonPanel = new ButtonPanel(Options.BASE_BUTTON_FONT_SIZE);
        background.add(buttonPanel, BorderLayout.WEST);

        // Panel contenant le leaderboard avec espace à droite (pour pas qu'il soit collé au bord)
        JPanel leaderboardWrapper = new JPanel();
        leaderboardWrapper.setLayout(new BorderLayout());
        leaderboardWrapper.setOpaque(false); // Fond transparent pour laisser voir le background
        leaderboardWrapper.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20)); // Ajout de marges internes

        // Conteneur du leaderboard
        leaderboardContainer = new JPanel();
        leaderboardContainer.setLayout(new BorderLayout());
        leaderboardContainer.setOpaque(false); // Fond transparent pour laisser voir le background
        leaderboardWrapper.add(leaderboardContainer, BorderLayout.CENTER);
        background.add(leaderboardWrapper, BorderLayout.EAST);

        // Initialisation du premier leaderboard (LeaderboardWorldwide)
        currentLeaderboard = new LeaderboardWorldWide();
        leaderboardContainer.add(currentLeaderboard, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Obtient le titre du menu principal.
     * 
     * @return Le titre du menu principal.
     */
    public Title getTitleLabel() {
        return titleLabel;
    }

    /**
     * Obtient le panneau contenant les boutons du menu principal.
     * 
     * @return Le panneau des boutons.
     */
    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}
