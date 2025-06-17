package fr.monkhanny.dorfromantik.components;

import fr.monkhanny.dorfromantik.utils.FontManager;

import javax.swing.*;
import java.awt.*;

/**
 * Classe utilitaire pour créer des boutons personnalisés.
 * Cette classe fournit des méthodes statiques permettant de générer des boutons stylisés avec du texte ou des icônes, en respectant une apparence cohérente.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 **/
public class Button {

    /**
     * Constructeur par défaut pour initialiser la classe utilitaire.
     */
    public Button() {
    }

    /**
     * Crée un bouton personnalisé avec un texte, une taille de police spécifique et un style prédéfini.
     * 
     * @param text     le texte à afficher sur le bouton
     * @param fontSize la taille de la police à utiliser pour le texte
     * @return un objet {@link JButton} stylisé
     */
    public static JButton createCustomTextButton(String text, float fontSize) {
        JButton button = new JButton(text);
        button.setFocusPainted(false); // Retirer le focus
        button.setBackground(new Color(102, 178, 255)); // Couleur de fond
        button.setForeground(Color.WHITE); // Couleur du texte
        button.setFont(FontManager.getButtonFont(fontSize)); // Appliquer la police du bouton
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espacement autour du texte du bouton
        return button;
    }

    /**
     * Crée un bouton personnalisé contenant une icône, avec un style prédéfini.
     * 
     * @param iconPath le chemin vers l'image de l'icône à afficher sur le bouton
     * @return un objet {@link JButton} contenant l'icône spécifiée
     */
    public static JButton createCustomIconButton(String iconPath) {
        JButton button = new JButton();
        button.setFocusPainted(false); // Retirer le focus

        // Charger l'icône depuis le chemin spécifié
        ImageIcon icon = new ImageIcon(iconPath);

        // Calculer automatiquement la taille de l'icône pour l'adapter à la taille du bouton
        int buttonWidth = 100;  // Taille du bouton (largeur)
        int buttonHeight = 100; // Taille du bouton (hauteur)

        // Redimensionner l'image de l'icône
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(resizedImage));

        // Couleur de fond (facultatif)
        button.setBackground(new Color(102, 178, 255));

        // Retirer la bordure du bouton (facultatif)
        button.setBorder(BorderFactory.createEmptyBorder());

        // Ajuster la taille du bouton
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        return button;
    }
}

