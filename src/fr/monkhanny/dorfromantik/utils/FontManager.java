package fr.monkhanny.dorfromantik.utils;

import fr.monkhanny.dorfromantik.enums.Fonts;

import java.awt.*;
import java.io.IOException;

/**
 * Classe utilitaire pour charger et gérer les polices personnalisées.
 * 
 * Cette classe permet de charger et d'appliquer des polices personnalisées pour les titres et les boutons.
 * Elle gère également l'ajustement automatique de la taille de la police en fonction de la taille des composants.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class FontManager {
    
    /**
     * Police pour les titres.
     */
    private static Font titleFont;

    /**
     * Police pour les boutons.
     */
    private static Font buttonFont;


    /**
     * Constructeur par défaut pour la classe FontManager.
     */
    public FontManager() {
        // Constructeur par défaut
    }

    /**
     * Charge une police personnalisée à partir du fichier de police spécifié.
     * 
     * @param fontEnum Enumération de la police à charger
     * @throws RuntimeException si le chargement de la police échoue
     * @throws IllegalStateException si la police est déjà chargée
     */
    public static void loadCustomFont(Fonts fontEnum) {
        try {
            Font loadedFont = FontLoader.loadFont(fontEnum);
            if (fontEnum == Fonts.TITLE) {
                titleFont = loadedFont;
            } else if (fontEnum == Fonts.BUTTON) {
                buttonFont = loadedFont;
            }
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException("Failed to load font: " + fontEnum, e);
        }
    }

    /**
     * Obtient la police du titre avec une taille spécifique.
     *
     * @param size La taille souhaitée pour la police du titre.
     * @return La police du titre redimensionnée à la taille donnée.
     * @throws IllegalStateException si la police du titre n'a pas été chargée.
     */
    public static Font getTitleFont(float size) {
        if (titleFont == null) {
            throw new IllegalStateException("Title font not loaded. Please load the font first.");
        }
        return titleFont.deriveFont(size);
    }

    /**
     * Obtient la police du bouton avec une taille spécifique.
     * 
     * @param size La taille souhaitée pour la police du bouton.
     * @return La police du bouton redimensionnée à la taille donnée.
     * @throws IllegalStateException si la police du bouton n'a pas été chargée.
     */
    public static Font getButtonFont(float size) {
        if (buttonFont == null) {
            throw new IllegalStateException("Button font not loaded. Please load the font first.");
        }
        return buttonFont.deriveFont(size);
    }

    
    /**
     * Ajuste la taille de la police du titre en fonction de la taille du composant donné.
     *
     * @param component Le composant dont la taille est utilisée pour ajuster la police.
     * @param minSize   La taille minimale de la police.
     * @param maxSize   La taille maximale de la police.
     * @return La police du titre ajustée à la taille calculée.
     * @throws IllegalStateException si la police du titre n'a pas été chargée.
     */
    public static Font getAdjustedTitleFont(Component component, float minSize, float maxSize) {
        if (titleFont == null) {
            throw new IllegalStateException("Title font not loaded. Please load the font first.");
        }
        float newSize = Math.max(minSize, Math.min(maxSize, component.getWidth() / 12f));
        return titleFont.deriveFont(newSize);
    }

    /**
     * Ajuste la taille de la police du bouton en fonction de la taille du composant donné.
     *
     * @param component Le composant dont la taille est utilisée pour ajuster la police.
     * @param minSize   La taille minimale de la police.
     * @param maxSize   La taille maximale de la police.
     * @return La police du bouton ajustée à la taille calculée.
     * @throws IllegalStateException si la police du bouton n'a pas été chargée.
     */
    public static Font getAdjustedButtonFont(Component component, float minSize, float maxSize) {
        if (buttonFont == null) {
            throw new IllegalStateException("Button font not loaded. Please load the font first.");
        }
        float newSize = Math.max(minSize, Math.min(maxSize, component.getHeight() / 20f));
        return buttonFont.deriveFont(newSize);
    }

    /**
     * Définit manuellement une police de titre personnalisée.
     *
     * @param font La police de titre personnalisée à définir.
     */
    public static void setTitleFont(Font font) {
        titleFont = font;
    }

    /**
     * Définit manuellement une police de bouton personnalisée.
     * 
     * @param font La police de bouton personnalisée à définir.
     */
    public static void setButtonFont(Font font) {
        buttonFont = font;
    }
}
