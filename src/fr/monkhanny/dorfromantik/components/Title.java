package fr.monkhanny.dorfromantik.components;

import fr.monkhanny.dorfromantik.utils.FontManager;

import javax.swing.*;
import java.awt.*;

/**
 * Composant personnalisé pour afficher un titre stylisé.
 * Cette classe étend {@link JLabel} et permet de créer des titres centrés avec une police et une couleur personnalisées.
 *
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class Title extends JLabel {

    /**
     * Crée un titre avec un texte, une taille de police et des styles par défaut.
     * Le texte est centré, et la couleur par défaut est blanche.
     *
     * @param text     Texte à afficher dans le titre
     * @param fontSize Taille de la police du titre
     * @see JLabel
     */
    public Title(String text, float fontSize) {
        super(text, SwingConstants.CENTER);
        setFont(FontManager.getTitleFont(fontSize)); // Appliquer la police
        setForeground(Color.WHITE); // Couleur du texte
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Marges
    }

    /**
     * Crée un titre avec un texte, une taille de police et une couleur de texte personnalisée.
     * Le texte est centré, et la couleur peut être spécifiée.
     *
     * @param text      Texte à afficher dans le titre
     * @param fontSize  Taille de la police du titre
     * @param textColor Couleur du texte
     * @see JLabel
     */
    public Title(String text, float fontSize, Color textColor) {
        super(text, SwingConstants.CENTER);
        setFont(FontManager.getTitleFont(fontSize)); // Appliquer la police
        setForeground(textColor); // Appliquer la couleur personnalisée
        setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Marges
    }

    /**
     * Met à jour la taille de la police du titre.
     * Cette méthode permet de changer dynamiquement la taille de la police utilisée.
     *
     * @param fontSize Nouvelle taille de la police
     */
    public void updateTitleFont(float fontSize) {
        setFont(FontManager.getTitleFont(fontSize)); // Mise à jour de la police
    }
}
