package fr.monkhanny.dorfromantik.gui;

import fr.monkhanny.dorfromantik.utils.FontManager;
import fr.monkhanny.dorfromantik.components.Button;
import fr.monkhanny.dorfromantik.controller.MainMenuMouseController;

import javax.swing.*;
import java.util.List;
import java.util.Arrays;

/**
 * Représente un panneau contenant les boutons du menu principal du jeu.
 * Ce panneau dispose d'une disposition verticale et intègre des boutons avec un style personnalisé.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class ButtonPanel extends JPanel {

    /** Bouton pour démarrer une nouvelle partie. */
    private JButton newGameButton;

    /** Bouton pour accéder aux instructions sur comment jouer. */
    private JButton howToPlayButton;

    /** Bouton pour accéder aux paramètres du jeu. */
    private JButton settingsButton;

    /** Bouton pour quitter l'application. */
    private JButton exitButton;

     /**
     * Constructeur qui initialise le panneau avec des boutons et applique un style personnalisé.
     * 
     * @param fontSize La taille de police initiale des boutons.
     */
    public ButtonPanel(float fontSize) {
        // Paramétrage de l'apparence du panneau
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setOpaque(false); // Rendre le panneau transparent
        this.setBorder(BorderFactory.createEmptyBorder(50, 30, 30, 30)); // Marge à gauche et en bas

        // Espacement vertical extensible pour centrer les boutons principaux verticalement
        this.add(Box.createVerticalGlue());

        // Créer les boutons avec un style personnalisé
        newGameButton = Button.createCustomTextButton("Jouer", fontSize);
        howToPlayButton = Button.createCustomTextButton("Comment jouer ?", fontSize);
        settingsButton = Button.createCustomTextButton("Paramètres", fontSize);
        exitButton = Button.createCustomTextButton("Quitter", fontSize);

        // Ajouter les boutons au panneau
        this.add(newGameButton);
        this.add(Box.createVerticalStrut(10)); // Espace entre les boutons
        this.add(howToPlayButton);
        this.add(Box.createVerticalStrut(10));
        this.add(settingsButton);
        this.add(Box.createVerticalStrut(10));
        this.add(exitButton);

        // Espacement extensible pour maintenir les icônes en bas
        this.add(Box.createVerticalGlue());

        @SuppressWarnings("unused")
        MainMenuMouseController gestionSouris = new MainMenuMouseController(this);
    }

    /**
     * Retourne le bouton "Jouer".
     *
     * @return Le bouton "Jouer".
     */
    public JButton getNewGameButton() {
        return newGameButton;
    }

    /**
     * Retourne le bouton "Comment jouer ?".
     *
     * @return Le bouton "Comment jouer ?".
     */
    public JButton getHowToPlayButton() {
        return howToPlayButton;
    }

    /**
     * Retourne le bouton "Paramètres".
     *
     * @return Le bouton "Paramètres".
     */
    public JButton getSettingsButton() {
        return settingsButton;
    }

    /**
     * Retourne le bouton "Quitter".
     *
     * @return Le bouton "Quitter".
     */
    public JButton getExitButton() {
        return exitButton;
    }

     /**
     * Retourne la liste de tous les boutons contenus dans ce panneau.
     *
     * @return Une liste des boutons : "Jouer", "Comment jouer ?", "Paramètres" et "Quitter".
     */
    public List<JButton> getButtons() {
        return Arrays.asList(newGameButton, howToPlayButton, settingsButton, exitButton);
    }

    /**
     * Met à jour la taille des polices des boutons en fonction de la largeur de la fenêtre.
     * Cette méthode ajuste dynamiquement la taille des polices pour s'adapter
     * à différents écrans ou résolutions.
     *
     * @param windowWidth La largeur actuelle de la fenêtre.
     */
    public void updateButtonFonts(int windowWidth) {
        // Mettre à jour la police des boutons avec la taille ajustée
        float newFontSize = windowWidth / 30f;
        newGameButton.setFont(FontManager.getTitleFont(newFontSize));
        howToPlayButton.setFont(FontManager.getTitleFont(newFontSize));
        settingsButton.setFont(FontManager.getTitleFont(newFontSize));
        exitButton.setFont(FontManager.getTitleFont(newFontSize));
    }
}
