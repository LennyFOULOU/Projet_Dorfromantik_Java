package fr.monkhanny.dorfromantik.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Arrays;

import fr.monkhanny.dorfromantik.enums.Biome;
import fr.monkhanny.dorfromantik.enums.TileOrientation;
import fr.monkhanny.dorfromantik.utils.Hexagon;
import fr.monkhanny.dorfromantik.utils.HexagonDrawer;

/**
 * Représente une tuile hexagonale dans le jeu.
 * 
 * Une tuile est définie par son emplacement sur le plateau, son rayon, et les biomes associés 
 * à chacun de ses côtés. Elle peut être dessinée graphiquement, pivotée, et possède des fonctionnalités
 * pour interagir avec ses tuiles voisines.
 * 
 * @version 1.0
 * @author Moncef STITI, Khalid CHENOUNA, Lenny FOULOU
 */
public class Tile extends Cell {

  /** Biomes associés aux côtés de la tuile. */
  private HashMap<TileOrientation, Biome> sideBiomes = new HashMap<>();

    /**
    * Constructeur pour une tuile avec des biomes spécifiques.
    * 
    * @param board  Le plateau de jeu auquel la tuile appartient.
    * @param x      Coordonnée X de la tuile.
    * @param y      Coordonnée Y de la tuile.
    * @param radius Rayon de la tuile.
    * @param biomes Liste des biomes affectés aux côtés de la tuile.
    */
  public Tile(Board board, int x, int y, int radius, Biome... biomes) {
    super(board, x, y, radius);
    TileOrientation[] sides = TileOrientation.values();
    for (int i = 0; i < sides.length; i++) {
      this.sideBiomes.put(sides[i], biomes[i]);
    }
  }

     /**
    * Constructeur pour une tuile avec des biomes assignés aléatoirement.
    * 
    * @param board  Le plateau de jeu auquel la tuile appartient.
    * @param x      Coordonnée X de la tuile.
    * @param y      Coordonnée Y de la tuile.
    * @param radius Rayon de la tuile.
    */
  public Tile(Board board, int x, int y, int radius) {
    super(board, x, y, radius);
    this.assignRandomBiomes();
  }

    /**
     * Constructeur pour une tuile positionnée en utilisant un point central, avec des biomes spécifiques.
     * 
     * @param board  Le plateau de jeu auquel la tuile appartient.
     * @param center Point central de la tuile.
     * @param radius Rayon de la tuile.
     * @param biomes Liste des biomes affectés aux côtés de la tuile.
     */
  public Tile(Board board, Point center, int radius, Biome... biomes) {
    this(board, center.x, center.y, radius, biomes);
  }

    /**
     * Constructeur pour une tuile positionnée en utilisant un point central, avec des biomes assignés aléatoirement.
     * 
     * @param board  Le plateau de jeu auquel la tuile appartient.
     * @param center Point central de la tuile.
     * @param radius Rayon de la tuile.
     */
  public Tile(Board board, Point center, int radius) {
    this(board, center.x, center.y, radius);
  }

    /**
     * Définit les biomes associés aux côtés de la tuile.
     * 
     * @param biomes Liste des biomes à assigner.
     */
  public void setBiomes(Biome... biomes) {
    TileOrientation[] sides = TileOrientation.values();
    for (int i = 0; i < sides.length; i++) {
      this.sideBiomes.put(sides[i], biomes[i]);
    }
  }

    /**
     * Assigne aléatoirement des biomes aux côtés de la tuile.
     * 
     * Utilise le moteur de jeu pour générer des biomes aléatoires, tout en garantissant
     * une répartition équilibrée entre deux biomes.
     */  
  public void assignRandomBiomes() {
    Game game = this.getBoard().getGame();
    Biome[] biomes = Biome.values();
    TileOrientation[] sides = TileOrientation.values();
    
    this.sideBiomes.clear();

    Biome firstBiome = biomes[game.getRandomInt(biomes.length)];
    biomes = Arrays.stream(biomes).filter(b -> b != firstBiome).toArray(Biome[]::new);
    Biome secondBiome = biomes[game.getRandomInt(biomes.length)];

    int firstBiomeSideCount = game.getRandomInt(sides.length + 1);
    int firstBiomeSideOffset = game.getRandomInt(sides.length);

    for (int i = 0; i < sides.length; i++) {
      TileOrientation side = sides[(i + firstBiomeSideOffset) % sides.length];
      Biome assignedBiome = (i < firstBiomeSideCount) ? firstBiome : secondBiome;
      this.sideBiomes.put(side, assignedBiome);
    }
  }

    /**
     * Obtient le biome associé à un côté spécifique de la tuile.
     * 
     * @param side Le côté de la tuile.
     * @return Le biome associé.
     */
  public Biome getBiome(TileOrientation side) {
    return this.sideBiomes.get(side);
  }

     /**
     * Obtient le biome dominant de la tuile.
     * 
     * @return Le biome dominant ou {@code null} en cas d'égalité parfaite.
     */
  public Biome getDominantBiome() {
    TileOrientation[] sides = TileOrientation.values();

    int firstBiomeCount = 0;
    int secondBiomeCount = 0;
    Biome firstBiome = this.getBiome(sides[0]);
    Biome secondBiome = null;

    for (TileOrientation side : sides) {
      Biome currentBiome = this.getBiome(side);
      if (currentBiome.equals(firstBiome)) {
        firstBiomeCount++;
      } else {
        secondBiome = currentBiome;
        secondBiomeCount++;
      }
    }

    if (firstBiomeCount > secondBiomeCount) {
      return firstBiome;
    } else if (firstBiomeCount < secondBiomeCount) {
      return secondBiome;
    }

    return null;
  }

  /**
   * Retourne les biomes associés à chaque côté de la tuile.
   * 
   * Cette méthode récupère les biomes pour tous les côtés de la tuile en respectant
   * l'ordre des orientations définies par l'énumération {@link TileOrientation}.
   * Les biomes sont placés dans un tableau où l'index correspond à l'ordre ordinal
   * de chaque orientation.
   * 
   * @return Un tableau de {@link Biome} représentant les biomes associés à chaque côté
   *         de la tuile. La longueur du tableau est égale au nombre de côtés dans 
   *         {@link TileOrientation}.
   */
  public Biome[] getBiomes() {
    Biome[] biomes = new Biome[TileOrientation.values().length];
    for (TileOrientation side : TileOrientation.values()) {
      biomes[side.ordinal()] = this.getBiome(side);
    }
    return biomes;
  }

    /**
     * Tourne la tuile dans le sens horaire ou anti-horaire.
     * 
     * @param clockwise {@code true} pour tourner dans le sens horaire, {@code false} sinon.
     */
  public void rotate(boolean clockwise) {
    TileOrientation[] sides = TileOrientation.values();
    HashMap<TileOrientation, Biome> newBiomesMap = new HashMap<>();

    for (int i = 0; i < sides.length; i++) {
      TileOrientation side = sides[i];
      TileOrientation newSide = clockwise ? sides[(i + 1) % sides.length] : sides[(i + sides.length - 1) % sides.length];
      newBiomesMap.put(newSide, this.sideBiomes.get(side));
    }

    this.sideBiomes = newBiomesMap;
    this.repaint();
  }

     /**
     * Vérifie si la tuile contient un biome donné.
     * 
     * @param biome Le biome recherché.
     * @return {@code true} si la tuile contient le biome, sinon {@code false}.
     */
  public boolean containsBiome(Biome biome) {
    for (TileOrientation side : TileOrientation.values()) {
      if (this.getBiome(side) == biome) {
        return true;
      }
    }
    return false;
  }

  /**
   * Vérifie si la tuile donnée est adjacente à la tuile actuelle.
   * 
   * Cette méthode calcule la distance euclidienne entre les coordonnées des deux
   * tuiles et détermine si la distance est suffisamment proche pour être considérée
   * comme adjacente dans une grille hexagonale. La distance attendue entre deux tuiles
   * adjacentes est calculée en fonction du rayon des tuiles et de la géométrie hexagonale.
   * 
   * @param otherTile La tuile à tester pour l'adjacence.
   * @return {@code true} si la tuile donnée est adjacente à la tuile actuelle, 
   *         sinon {@code false}.
   */
  public boolean isAdjacentTo(Tile otherTile) {
    
    int radius = this.getRadius(); // Utilisation du rayon pour la distance correcte

    // Calculer les différences de position
    int deltaX = otherTile.getXCoord() - this.getXCoord();
    int deltaY = otherTile.getYCoord() - this.getYCoord();

    // Calculer la distance euclidienne entre les deux tuiles
    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

    // Distance attendue pour des tuiles adjacentes dans une grille hexagonale
    double expectedDistance = (int) (1.5 * radius); // ou une autre valeur calculée correctement

    // Définir une tolérance pour la précision
    double epsilon = 15.0;

    // Vérifier si la différence de distance est dans la tolérance
    if (Math.abs(distance - expectedDistance) < epsilon) {
        return true;
    }
    return false;
}

  /**
   * Récupère la tuile voisine dans une direction donnée.
   * 
   * Cette méthode calcule les coordonnées de la tuile voisine en fonction de
   * la direction spécifiée (orientation) et de la distance relative dans une
   * grille hexagonale. Elle utilise le rayon de la tuile pour déterminer la
   * distance correcte.
   * 
   * @param orientation La direction (orientation) du voisin souhaité par rapport à la tuile actuelle.
   *                    Les valeurs possibles sont définies dans {@link TileOrientation}.
   * @return La tuile voisine dans la direction spécifiée, ou {@code null} si aucune tuile n'existe à cet endroit.
   */
public Tile getNeighbor(TileOrientation orientation) {
  int radius = this.getRadius();
  int neighborX = this.getXCoord();
  int neighborY = this.getYCoord();

  if (orientation == TileOrientation.NORTH) {
      neighborY -= (int) (Math.sqrt(3) * radius);
  } else if (orientation == TileOrientation.NORTH_EAST) {
      neighborX += (int) (1.5 * radius);
      neighborY -= (int) (Math.sqrt(3) / 2 * radius);
  } else if (orientation == TileOrientation.SOUTH_EAST) {
      neighborX += (int) (1.5 * radius);
      neighborY += (int) (Math.sqrt(3) / 2 * radius);
  } else if (orientation == TileOrientation.SOUTH) {
      neighborY += (int) (Math.sqrt(3) * radius);
  } else if (orientation == TileOrientation.SOUTH_WEST) {
      neighborX -= (int) (1.5 * radius);
      neighborY += (int) (Math.sqrt(3) / 2 * radius);
  } else if (orientation == TileOrientation.NORTH_WEST) {
      neighborX -= (int) (1.5 * radius);
      neighborY -= (int) (Math.sqrt(3) / 2 * radius);
  }

  // Rechercher la tuile à la position calculée
  return this.getBoard().getTileAt(neighborX, neighborY);
}


    /**
     * Détermine le côté de la tuile en fonction d'une position (x, y).
     * 
     * @param x Coordonnée X relative.
     * @param y Coordonnée Y relative.
     * @return Le côté correspondant.
     */
  public TileOrientation determineSide(int x, int y) {
    int radius = this.getRadius();
    TileOrientation[] sides = TileOrientation.values();
    double angle = Cell.to360Degrees(Math.toDegrees(Math.atan2(y - radius, x - radius)) + 120);

    int floorSide = (int) Math.floor(Cell.to360Degrees(angle - 2) / 60);
    int ceilSide = (int) Math.floor(Cell.to360Degrees(angle + 2) / 60);

    if (floorSide == ceilSide) {
      return sides[floorSide];
    }

    Biome floorBiome = this.getBiome(sides[floorSide]);
    Biome dominantBiome = this.getDominantBiome();

    if (dominantBiome == null && y > radius) {
      return TileOrientation.SOUTH;
    }

    if (dominantBiome == null && y < radius) {
      return TileOrientation.NORTH;
    }

    return floorBiome.equals(dominantBiome) ? sides[ceilSide] : sides[floorSide];
  }

    /**
     * Dessine la tuile à une position donnée avec une échelle spécifique.
     * 
     * @param g     Contexte graphique.
     * @param x     Coordonnée X où dessiner.
     * @param y     Coordonnée Y où dessiner.
     * @param scale Échelle du dessin.
     */
  protected void drawTileAt(Graphics g, int x, int y, float scale) {
      // Sauvegarde de l'état actuel du graphique
      Graphics2D g2d = (Graphics2D) g.create();

      // Déplacement du contexte graphique à la position souhaitée
      g2d.translate(x, y);

      // Appel de la méthode de dessin de la tuile à la nouvelle position
      paintTile(g2d, scale);
      g2d.dispose();
  }


  /**
   * Méthode principale de dessin de la tuile.
   * 
   * @param g     Le contexte graphique
   * @param scale L'échelle de la tuile
   */
  protected void paintTile(Graphics g, float scale) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int radius = this.getRadius();
        Point center = new Point(radius, radius);
        radius = (int) (radius * scale);
        Hexagon hexagon = new Hexagon(center, radius);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(hexagon);

        HexagonDrawer hexDrawer = new HexagonDrawer(this);
        hexDrawer.drawHexagon(g2d, radius, center);

        g2d.setClip(null);
        g2d.setStroke(new BasicStroke((int) radius / 15));
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(hexagon);

        g2d.dispose();
    }

}
