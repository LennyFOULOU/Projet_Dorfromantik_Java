package fr.monkhanny.dorfromantik.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe utilitaire pour charger et lire des fichiers audio.
 * 
 * Cette classe fournit une méthode statique pour charger un fichier audio
 * et renvoyer un objet `Clip` qui peut être utilisé pour lire le son.
 * 
 * @author Moncef STITI
 * @version 1.0
 */
public class SoundLoader {

    /**
     * Constructeur par défaut pour la classe SoundLoader.
     */
    public SoundLoader() {
        // Constructeur par défaut
    }

    /**
     * Charge un fichier audio à partir du chemin spécifié.
     * 
     * @param filePath Chemin du fichier audio à charger
     * @return Clip Objet `Clip` qui peut être utilisé pour lire le son
     */
    public static Clip loadMusic(String filePath) {
        try {
            // Charger le fichier audio
            File soundFile = new File(filePath);
            // Créer un flux audio à partir du fichier et l'ouvrir
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Failed to load sound at path: " + filePath);
            return null;
        }
    }

}