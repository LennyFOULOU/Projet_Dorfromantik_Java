package fr.monkhanny.dorfromantik.game;

import fr.monkhanny.dorfromantik.enums.Biome;
import java.util.*;

/**
 * Classe Pocket, représentant une poche connectée de tuiles partageant le même biome.
 * Une Pocket permet de regrouper et de gérer un ensemble de tuiles liées par leur type de biome.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */

public class Pocket {
    private Biome biome;
    private Set<Tile> tiles;

    /**
     * Constructeur de la classe Pocket.
     * Initialise une poche avec un biome spécifié et un ensemble vide de tuiles.
     * 
     * @param biome Le biome partagé par toutes les tuiles de cette poche.
     */

    public Pocket(Biome biome) {
        this.biome = biome;
        this.tiles = new HashSet<>();
    }

    /**
     * Ajoute une tuile à cette poche.
     * 
     * @param tile La tuile à ajouter.
     */

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    /**
     * Retourne la taille de la poche, c'est-à-dire le nombre de tuiles qu'elle contient.
     * 
     * @return Le nombre de tuiles dans cette poche.
     */

    public int getSize() {
        return tiles.size();
    }

    /**
     * Retourne le biome de cette poche.
     * 
     * @return Le biome de la poche.
     */

    public Biome getBiome() {
        return biome;
    }

    /**
     * Retourne l'ensemble des tuiles contenues dans cette poche.
     * 
     * @return Un ensemble des tuiles de cette poche.
     */

    public Set<Tile> getTiles() {
        return tiles;
    }
}
