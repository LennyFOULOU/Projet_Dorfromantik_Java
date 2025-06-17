package fr.monkhanny.dorfromantik.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Classe utilitaire pour charger des images à partir de fichiers.
 * 
 * @version 1.0
 * @author Moncef STITI
 */
public class ImageLoader {
    
    /**
     * Icône de l'application.
     */
    public static final Image APPLICATION_ICON = ImageLoader.loadImage("./ressources/images/Application/Application_Icon.jpg");


    /**
     * Constructeur par défaut pour la classe ImageLoader.
     */
    public ImageLoader() {
        // Constructeur par défaut
    }

    /**
     * Charge une image à partir du fichier spécifié.
     * 
     * @param filePath Chemin du fichier image à charger.
     * @return L'image chargée, ou null si une erreur se produit.
     */
    public static Image loadImage(String filePath) {
        try {
            File imageFile = new File(filePath);
            return ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
            return null;
        }
    }
}
