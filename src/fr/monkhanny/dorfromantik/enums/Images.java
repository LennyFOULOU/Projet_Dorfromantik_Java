package fr.monkhanny.dorfromantik.enums;

/**
 * Enumération représentant les différentes images utilisées dans l'application.
 * Chaque valeur de l'énumération correspond à une image spécifique (icônes et gifs).
 * Cette classe permet de récupérer le chemin d'accès aux fichiers d'images associés.
 *
 * @version 1.0
 * @author Lenny FOULOU
 */
public enum Images {
    /**
     * Icône des paramètres.
     */
    SETTINGS_ICON,

    /**
     * Icône de sortie.
     */
    EXIT_ICON,

    /**
     * Gif de la première étape du tutoriel.
     */
    TUTORIAL_GIF1,

    /**
     * Gif de la deuxième étape du tutoriel.
     */
    TUTORIAL_GIF2,

    /**
     * Gif de la troisième étape du tutoriel.
     */
    TUTORIAL_GIF3,

    /**
     * Gif de la quatrième étape du tutoriel.
     */
    TUTORIAL_GIF4,

    /**
     * Gif de la cinquième étape du tutoriel.
     */
    TUTORIAL_GIF5,

    /**
     * Gif de la sixième étape du tutoriel.
     */
    TUTORIAL_GIF6;



    /**
     * Récupère le chemin du fichier d'image correspondant à l'énumération.
     *
     * @return Le chemin du fichier d'image
     */
    public String getImagePath() {
        switch (this) {
            case SETTINGS_ICON:
                return "./ressources/images/Icone/SettingsIcon.png";
            case EXIT_ICON:
                return "./ressources/images/Icone/ExitIcon.png";
            case TUTORIAL_GIF1:
                return "./ressources/images/Tutorial/Gif1.gif";
            case TUTORIAL_GIF2:
                return "./ressources/images/Tutorial/Gif2.gif";
            case TUTORIAL_GIF3:
                return "./ressources/images/Tutorial/Gif3.gif";
            case TUTORIAL_GIF4:
                return "./ressources/images/Tutorial/Gif4.gif";
            case TUTORIAL_GIF5:
                return "./ressources/images/Tutorial/Gif5.gif";
            case TUTORIAL_GIF6:
                return "./ressources/images/Tutorial/Gif6.gif";
            default:
                throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
