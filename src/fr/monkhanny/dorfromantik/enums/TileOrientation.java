package fr.monkhanny.dorfromantik.enums;

/**
 * Enumération représentant les différentes orientations possibles pour une tuile dans le jeu.
 * Chaque orientation est définie par une direction cardinal ou intercardinale.
 *
 * @version 1.0
 * @author Lenny FOULOU
 */
public enum TileOrientation {
    /**
     * Orientation vers le nord.
     */
    NORTH,

    /**
     * Orientation vers le nord-est.
     */
    NORTH_EAST,

    /**
     * Orientation vers le sud-est.
     */
    SOUTH_EAST,

    /**
     * Orientation vers le sud.
     */
    SOUTH,

    /**
     * Orientation vers le sud-ouest.
     */
    SOUTH_WEST,

    /**
     * Orientation vers le nord-ouest.
     */
    NORTH_WEST;

    /**
     * Retourne l'orientation opposée à celle-ci.
     * Par exemple, si l'orientation actuelle est NORTH, l'orientation opposée sera SOUTH.
     *
     * @return L'orientation opposée.
     * @throws IllegalArgumentException Si l'orientation est inconnue.
     */
    public TileOrientation oppositeOrientation() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case NORTH_EAST:
                return SOUTH_WEST;
            case SOUTH_EAST:
                return NORTH_WEST;
            case SOUTH:
                return NORTH;
            case SOUTH_WEST:
                return NORTH_EAST;
            case NORTH_WEST:
                return SOUTH_EAST;
            default:
                throw new IllegalArgumentException("Unknown TileOrientation: " + this);
        }
    }
}
