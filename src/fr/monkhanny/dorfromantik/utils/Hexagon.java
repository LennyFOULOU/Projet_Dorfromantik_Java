package fr.monkhanny.dorfromantik.utils;

import java.awt.Point;
import java.awt.Polygon;

/**
 * Classe représentant un hexagone. 
 * 
 * Cette classe est une extension de la classe {@link Polygon}
 * et permet de créer un polygone ayant la forme d'un hexagone, en spécifiant les coordonnées
 * du centre, le rayon ainsi qu'un angle de départ optionnel.
 * 
 * @version 1.0
 * @author Khalid CHENOUNA
 */
public class Hexagon extends Polygon {
  
  /**
   * Angle entre deux sommets de l'hexagone
   */
  private static final int ANGLE_BETWEEN_VERTICES = 60;

  /**
   * Constructeur d'un hexagone
   * 
   * @param x          Position x du centre de l'hexagone
   * @param y          Position y du centre de l'hexagone
   * @param radius     Rayon de l'hexagone
   * @param startAngle Angle de départ de l'hexagone
   */
  public Hexagon(int x, int y, int radius, double startAngle) {
    if (radius <= 0) {
      throw new IllegalArgumentException("Le rayon doit être supérieur à zéro.");
    }

    for (int i = 0; i < 6; i++) {
      double angleRad = calculateAngle(i, startAngle);
      this.addPoint(
          (int) (x + radius * Math.cos(angleRad)),
          (int) (y + radius * Math.sin(angleRad))
      );
    }
  }

  /**
   * Calcule l'angle en radians pour un sommet donné
   * 
   * @param vertexIndex Index du sommet (0 à 5)
   * @param startAngle  Angle de départ
   * @return Angle en radians
   */
  private double calculateAngle(int vertexIndex, double startAngle) {
    return Math.toRadians(vertexIndex * ANGLE_BETWEEN_VERTICES + startAngle);
  }

  /**
   * Constructeur d'un hexagone
   * 
   * @param center     Centre de l'hexagone
   * @param radius     Rayon de l'hexagone
   * @param startAngle Angle de départ de l'hexagone
   */
  public Hexagon(Point center, int radius, double startAngle) {
    this(center.x, center.y, radius, startAngle);
  }

  /**
   * Constructeur d'un hexagone
   * 
   * @param x      Position x du centre de l'hexagone
   * @param y      Position y du centre de l'hexagone
   * @param radius Rayon de l'hexagone
   */
  public Hexagon(int x, int y, int radius) {
    this(x, y, radius, 0);
  }

  /**
   * Constructeur d'un hexagone
   * 
   * @param center Centre de l'hexagone
   * @param radius Rayon de l'hexagone
   */
  public Hexagon(Point center, int radius) {
    this(center.x, center.y, radius, 0);
  }
}
