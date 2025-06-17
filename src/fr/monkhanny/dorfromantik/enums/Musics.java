package fr.monkhanny.dorfromantik.enums;

/**
 * Enumération représentant les différentes musiques utilisées dans l'application.
 * Chaque valeur de l'énumération correspond à une musique spécifique.
 * Cette classe permet de récupérer le chemin d'accès au fichier de musique associé.
 *
 * @version 1.0
 * @author Moncef STITI
 */
public enum Musics {
    /**
     * Musique du menu principal.
     */
    MAIN_MENU_MUSIC;

    /**
     * Récupère le chemin du fichier de musique correspondant à l'énumération.
     *
     * @return Le chemin du fichier de musique
     */
    public String getSoundsPath() {
        switch (this) {
            case MAIN_MENU_MUSIC:
                return "./ressources/sounds/Music/mainMenuMusic.wav";
            default:
                throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
