package fr.monkhanny.dorfromantik.game;

import fr.monkhanny.dorfromantik.components.Title;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe représentant le menu d'échappement (pause) dans le jeu.
 * Fournit une interface utilisateur permettant de reprendre le jeu, accéder aux paramètres ou quitter vers le menu principal.
 *
 * @version 1.0
 * @author Khalid CHENOUNA
 */

public class EscapeMenu extends JFrame {

    /**
     * Le bouton permettant de reprendre la partie en cours.
     */
    private JButton resumeButton;

    /**
     * Le bouton permettant de retourner au menu principal.
     */
    private JButton mainMenuButton;

    /**
     * Le bouton permettant d'accéder aux paramètres du jeu.
     */
    private JButton settingsButton;

     /**
     * Constructeur de la fenêtre du menu d'échappement.
     *
     * @param gameFrame La fenêtre principale du jeu (parent).
     * @param game      L'instance du jeu en cours.
     */

    public EscapeMenu(JFrame gameFrame, Game game) {
        // Paramétrer la fenêtre de la pause
        setTitle("Menu de Pause");
        setSize(550, 200);  // Taille de la fenêtre
        setMaximumSize(new Dimension(550,200));
        setMinimumSize(new Dimension(550,200));
        setLocationRelativeTo(gameFrame); // Centrer la fenêtre sur le jeu
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Fermer sans quitter l'application
        setLayout(new BorderLayout()); // Utiliser BorderLayout pour gérer les composants

        // Ajouter l'image de fond
        JLabel background = new JLabel(new ImageIcon("./ressources/images/MainMenu/backgroundBlured.jpg"));
        background.setLayout(new BorderLayout());  
        this.add(background);

        // Créer un panneau principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);  // Garder le fond transparent
        background.add(panel, BorderLayout.CENTER); // Ajouter le panneau dans le centre de l'image

        // Ajouter le message "Jeu en pause" centré en haut
        Title pauseLabel = new Title("Jeu en pause", 34f);
        panel.add(pauseLabel, BorderLayout.NORTH);  // Placer le titre dans la partie nord du BorderLayout

        // Créer le panneau des boutons avec un FlowLayout horizontal
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);  // Garder le fond transparent

        // Créer les boutons et ajouter les écouteurs d'événements
        resumeButton = createButton("Reprendre");
        settingsButton = createButton("Paramètres");
        mainMenuButton = createButton("Quitter");

        // Ajouter les boutons dans le panneau
        buttonPanel.add(resumeButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(mainMenuButton);

        // Ajouter le panneau des boutons au panneau principal
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true); // Afficher la fenêtre
    }

    /**
     * Méthode utilitaire pour créer un bouton stylisé.
     *
     * @param text Le texte à afficher sur le bouton.
     * @return Un bouton configuré.
     */

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 18));
        button.setBackground(new Color(0, 122, 255));  // Bleu lumineux pour les boutons
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));  // Bordure blanche
        button.setPreferredSize(new Dimension(150, 50));  // Taille des boutons
        return button;
    }

    /**
     * Définit un écouteur d'événement pour le bouton "Reprendre".
     *
     * @param listener L'écouteur d'événement à associer.
     */

    public void setResumeButtonListener(ActionListener listener) {
        resumeButton.addActionListener(listener);
    }

    /**
     * Définit un écouteur d'événement pour le bouton "Quitter".
     *
     * @param listener L'écouteur d'événement à associer.
     */

    public void setQuitButtonListener(ActionListener listener) {
        mainMenuButton.addActionListener(listener);
    }

    /**
     * Définit un écouteur d'événement pour le bouton "Paramètres".
     *
     * @param listener L'écouteur d'événement à associer.
     */

    public void setSettingsButtonListener(ActionListener listener) {
        settingsButton.addActionListener(listener);
    }

    /**
     * Modifie la visibilité du menu d'échappement.
     *
     * @param visible {@code true} pour afficher le menu, {@code false} pour le masquer.
     */

    public void setMenuVisible(boolean visible) {
        setVisible(visible);
    }
}
