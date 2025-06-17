package fr.monkhanny.dorfromantik;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import fr.monkhanny.dorfromantik.gui.MainMenu;

/**
 * Classe contenant les constantes et les variables globales pour configurer les différentes options du jeu.
 * Elle est utilisée pour gérer les paramètres d'affichage, les animations, les paramètres audio,
 * ainsi que l'état général de la fenêtre et du jeu.
 * @version 1.0
 * @author Moncef STITI, Lenny FOULOU
 * 
 */
public class Options {

    /**
     * Constructeur par défaut pour la classe Options.
     */
    public Options() {
        // Constructeur par défaut
    }

    /**
     * Taille de police de base pour les titres du menu principal
     */
    public static final float BASE_TITLE_FONT_SIZE = 80f;

    /**
     * Taille de police de base pour les boutons du menu principal
     */
    public static final float BASE_BUTTON_FONT_SIZE = 45f;

    /**
     * Niveau de volume par défaut
     */
    public static final int DEFAULT_VOLUME = 60;

    /**
    * Musique en sourdine ou non
    */
    public static boolean MUSIC_MUTED = false;

    /**
    * Sons en sourdine ou non
    */
    public static boolean SOUNDS_MUTED = false;

    /**
     * Couleur de subrillance des boutons
     */
    public static final Color BUTTON_HOVER_COLOR = new Color(0, 130, 180);

    /**
     * Taille de police pour les boutons du menu principal (quand survolés)
     */
    public static final float HOVER_FONT_SCALE = 1.1f; 

    /**
     * Nombre d'étapes pour l'animation
     */
    public static final int ANIMATION_STEPS = 10;

    /**
     * Délai entre chaque étape de l'animation
     */
    public static final int ANIMATION_DELAY = 15;

    /**
     * Volume de la musique
     */
    public static int MUSIC_VOLUME = 50;

    /**
     * Référence à la fenêtre des paramètres
     */
    public static JFrame settingsFrame;

    /**
     * Volume des bruitages
     */
    public static int SOUNDS_VOLUME = 50;

    /**
     * Taille minimum de la fenêtre du jeu
     */
    public static final Dimension MINIMUM_FRAME_SIZE = new Dimension(1200, 800);

    /**
     * Indique si le mode auto focus est activé
     */
    public static boolean AUTO_FOCUS = false;

    /**
     * Nombre maximum de tuiles dans le jeu (tuile initiale comprise)
     */
    public static final int MAX_TILE_NUMBER = 50;

    /**
     * Indique si le jeu est en plein écran
     */
    public static boolean FULL_SCREEN = false;

    /**
     * Taille de police pour les scores
     */
    public static final float SCORE_SIZE = 30f;

    /**
     * Graine pour la génération aléatoire
     */
    public static long SEED = 0;

    /**
     * Référence au menu principal
     */
    public static MainMenu mainMenu;

    /**
     * Indique si le jeu est en cours
     */
    public static boolean isPlaying = false;

    /**
     * Indique si le jeu est en pause
     */
    public static boolean isPaused = false;
}
