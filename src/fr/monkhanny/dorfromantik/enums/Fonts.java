package fr.monkhanny.dorfromantik.enums;

import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;

/**
 * Enumération représentant les différentes polices utilisées dans l'application.
 * Les valeurs de l'énumération sont : TITLE, BUTTON et SCORE, chacune correspondant à un style de police spécifique.
 * Cette classe permet de récupérer le chemin d'accès aux fichiers de police et de charger une police à une taille donnée.
 *
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public enum Fonts {
    /**
     * Police utilisée pour les titres.
     */
    TITLE,

    /**
     * Police utilisée pour les boutons.
     */
    BUTTON,

    /**
     * Police utilisée pour les scores.
     */
    SCORE;

    /**
     * Récupère le chemin du fichier de police correspondant à l'énumération.
     *
     * @return Le chemin du fichier de police
     */
    public String getFontPath() {
        switch (this) {
            case TITLE:
                return "./ressources/fonts/Contage-Black.ttf";
            case BUTTON:
                return "./ressources/fonts/Contage-Regular.ttf";
            case SCORE:
                return "./ressources/fonts/Contage-Bold.ttf";
            default:
                throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }

    /**
     * Charge la police correspondante à l'énumération et ajuste sa taille.
     * Si une erreur se produit lors du chargement de la police, une police de secours (Arial) est utilisée.
     *
     * @param size La taille souhaitée de la police
     * @return L'objet Font correspondant à l'énumération et à la taille donnée
     */
    public Font getFont(float size) {
        try {
            switch (this) {
                case TITLE:
                    return Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/fonts/Contage-Black.ttf")).deriveFont(size);
                case BUTTON:
                    return Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/fonts/Contage-Regular.ttf")).deriveFont(size);
                case SCORE:
                    return Font.createFont(Font.TRUETYPE_FONT, new File("./ressources/fonts/Contage-Bold.ttf")).deriveFont(size);
                default:
                    throw new IllegalArgumentException("Unexpected value: " + this);
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Retourner une police de secours si le fichier est introuvable ou s'il y a une erreur
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }
}
