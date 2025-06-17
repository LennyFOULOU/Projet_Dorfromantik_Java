package fr.monkhanny.dorfromantik.utils;

import fr.monkhanny.dorfromantik.enums.Fonts;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe utilitaire pour charger des polices à partir de fichiers.
 * 
 * Cette classe fournit une méthode statique pour charger une police à partir d'un fichier
 * @version 1.0
 * @author Moncef STITI
 * @see Fonts
 * @see Font
 */
public class FontLoader {

    /**
     * Constructeur par défaut pour la classe FontLoader.
     */
    public FontLoader() {
        // Constructeur par défaut
    }

    /**
     * Charge une police à partir du fichier spécifié.
     * @param fontEnumName Enumération de la police à charger.
     * @return La police chargée.
     * @throws IOException Si une erreur se produit lors de la lecture du fichier.
     * @throws FontFormatException Si une erreur se produit lors de la création de la police.
     */
    public static Font loadFont(Fonts fontEnumName) throws IOException, FontFormatException {
        String fontFilePath = fontEnumName.getFontPath();
        File fontFile = new File(fontFilePath);
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(customFont);
        return customFont;
    }
}
