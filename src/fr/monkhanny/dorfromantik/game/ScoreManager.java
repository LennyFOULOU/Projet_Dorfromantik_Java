package fr.monkhanny.dorfromantik.game;

import fr.monkhanny.dorfromantik.enums.Biome;
import fr.monkhanny.dorfromantik.enums.TileOrientation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Gère le score du jeu en suivant les poches de biomes et en calculant le score en fonction de la taille de ces poches.
 * 
 * @version 1.0
 * @author Lenny FOULOU
 */
public class ScoreManager {
    private List<Pocket> pockets;
    private int currentScore;

    /**
     * Constructeur de la classe ScoreManager.
     * Initialise les poches et le score à 0.
     */

    public ScoreManager() {
        pockets = new ArrayList<>();
        currentScore = 0;
    }

    /**
     * Ajoute une tuile à la poche appropriée ou crée une nouvelle poche si nécessaire.
     * Le score est recalculé après l'ajout de la tuile.
     *
     * @param tile La tuile à ajouter.
     */
    public void addTile(Tile tile) {
        Map<Biome, Pocket> biomeToPocketMap = new HashMap<>();
    
        // Trouver les poches existantes auxquelles cette tuile peut être connectée, en vérifiant les voisins directs
        for (TileOrientation orientation : TileOrientation.values()) {
            Biome biome = tile.getBiome(orientation);
            if (biome != null) {
                // Trouver la tuile voisine dans la direction donnée
                Tile neighborTile = tile.getNeighbor(orientation);
                if (neighborTile != null && neighborTile.getBiome(orientation.oppositeOrientation()) == biome) {
                    Pocket connectedPocket = findPocketForTile(neighborTile, biome);
                    if (connectedPocket != null && !biomeToPocketMap.containsKey(biome)) {
                        biomeToPocketMap.put(biome, connectedPocket);
                    }
                }
            }
        }
    
        // Ajouter la tuile aux poches existantes ou créer une nouvelle poche si aucune n'est trouvée
        for (TileOrientation orientation : TileOrientation.values()) {
            Biome biome = tile.getBiome(orientation);
            if (biome != null) {
                if (biomeToPocketMap.containsKey(biome)) {
                    // Ajouter la tuile à la poche existante pour ce biome
                    biomeToPocketMap.get(biome).addTile(tile);
                } else {
                    // Créer une nouvelle poche pour ce biome
                    Pocket newPocket = new Pocket(biome);
                    newPocket.addTile(tile);
                    pockets.add(newPocket);
                    biomeToPocketMap.put(biome, newPocket);
                }
            }
        }
    
        // Recalculer le score après avoir ajouté la tuile
        recalculateScore();
    }
    
    /**
     * Trouve la poche associée à la tuile et au biome donnés.
     *
     * @param tile  La tuile à vérifier.
     * @param biome Le biome à associer.
     * @return La poche associée à la tuile et au biome, ou null si aucune poche n'est trouvée.
     */
    private Pocket findPocketForTile(Tile tile, Biome biome) {
        for (Pocket pocket : pockets) {
            if (pocket.getBiome() == biome && pocket.getTiles().contains(tile)) {
                return pocket;
            }
        }
        return null;
    }

    /**
     * Recalcule le score en fonction des poches de biomes actuelles.
     */
    private void recalculateScore() {
        currentScore = 0;
        for (Pocket pocket : pockets) {
            currentScore += Math.pow(pocket.getSize(), 2);
        }
    }

    /**
     * Obtient le score actuel du jeu.
     * 
     * @return Le score actuel du jeu.
     */

    public int getCurrentScore() {
        return currentScore;
    }
}