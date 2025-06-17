package fr.monkhanny.dorfromantik.enums;

import java.awt.Color;

/**
 * Enumération des différents biomes possibles pour les tuiles.
 * 
 * @version 1.0
 * @author Moncef STITI
 * @see Color
 */
public enum Biome {
  /**
   * Biome de la mer
   */
  SEA, 

  /**
   * Biome de la prairie
   */
  FIELD, 
  
  /**
   * Biome de la prairie
   */
  PRE, 
  
  /**
   * Biome de la forêt
   */
  FOREST, 
  
  /**
   * Biome de la montagne
   */
  MOUNTAIN;

  /**
   * Retourne les couleurs associées au biome.
   * 
   * @return Tableau de couleurs associées au biome
   */
  public Color[] getBiomeColors() {
    switch (this) {
      case SEA:
        return new Color[] { new Color(30, 144, 255), new Color(70, 160, 255), new Color(0, 119, 190) };
      case FIELD:
        return new Color[] { new Color(243, 223, 72), new Color(255, 235, 90), new Color(215, 200, 50) };
      case PRE:
        return new Color[] { new Color(120, 200, 120), new Color(140, 220, 140), new Color(100, 180, 100) };
      case FOREST:
        return new Color[] { new Color(34, 139, 76), new Color(50, 160, 95), new Color(20, 120, 60) };
      case MOUNTAIN:
        return new Color[] { new Color(128, 128, 128), new Color(150, 150, 150), new Color(100, 100, 100) };
      default:
        throw new IllegalArgumentException("Unknown biome : " + this); 
    }
  }
}
