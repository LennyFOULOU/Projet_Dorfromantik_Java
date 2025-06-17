package fr.monkhanny.dorfromantik.enums;

/**
 * Enumération représentant les différents effets sonores utilisés dans l'application.
 * Chaque valeur de l'énumération correspond à un effet sonore spécifique.
 * Cette classe permet de récupérer le chemin d'accès au fichier sonore associé.
 *
 * @version 1.0
 * @author Moncef STITI
 */
public enum Sounds {
    /**
     * Effet sonore 1.
     */
    SOUNDS1,

    /**
     * Effet sonore 2.
     */
    SOUNDS2;

    /**
     * Récupère le chemin du fichier sonore correspondant à l'énumération.
     *
     * @return Le chemin du fichier sonore
     */
    public String getSoundsPath() {
        switch (this) {
            case SOUNDS1:
                return "./ressources/sounds/SFX/1.wav";
            case SOUNDS2:
                return "./ressources/sounds/SFX/2.wav";
            default:
                throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
