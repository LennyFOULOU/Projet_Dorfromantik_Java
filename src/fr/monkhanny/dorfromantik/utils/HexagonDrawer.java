package fr.monkhanny.dorfromantik.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import fr.monkhanny.dorfromantik.enums.Biome;
import fr.monkhanny.dorfromantik.game.Tile;
import fr.monkhanny.dorfromantik.enums.TileOrientation;

/**
 * Classe utilitaire pour dessiner un hexagone et ses différentes facettes.
 * 
 * Cette classe est utilisée pour dessiner une tuile hexagonale représentant les différentes biomes.
 * Elle permet de gérer l'affichage graphique des tuiles en fonction de leurs caractéristiques.
 * 
 * @version 1.0
 * @author Lenny FOULOU, Khalid CHENOUNA
 */
public class HexagonDrawer {
    
    /**
     * Tuile à dessiner.
     */
    private Tile tile;

    /**
     * Constructeur pour initialiser la tuile à dessiner.
     *
     * @param tile Tuile à dessiner
     */
    public HexagonDrawer(Tile tile) {
        this.tile = tile;
    }

    /**
     * Dessine un hexagone à partir du centre et du rayon spécifiés.
     * 
     * @param g2d    Contexte graphique pour dessiner l'hexagone
     * @param radius Rayon de l'hexagone
     * @param center Centre de l'hexagone
     */
    public void drawHexagon(Graphics2D g2d, double radius, Point center) {
        double hexRadius = radius / Math.sqrt(3) / 3;
        double paddingX = center.x - radius;
        double paddingY = center.y - radius;

        drawHexagonRow(g2d, paddingX + radius * 0.5, paddingY + radius - radius * Math.sqrt(3) / 2, hexRadius, 4);
        drawHexagonRow(g2d, paddingX, paddingY + radius - radius * Math.sqrt(3) / 3, hexRadius, 6);
        drawHexagonRow(g2d, paddingX - radius * 0.5, paddingY + radius - radius * Math.sqrt(3) / 6, hexRadius, 8);
        drawHexagonRow(g2d, paddingX - radius, paddingY + radius, hexRadius, 10);
        drawHexagonRow(g2d, paddingX - radius * 0.5, paddingY + radius + radius * Math.sqrt(3) / 6, hexRadius, 8);
        drawHexagonRow(g2d, paddingX, paddingY + radius + radius * Math.sqrt(3) / 3, hexRadius, 6);
        drawHexagonRow(g2d, paddingX + radius * 0.5, paddingY + radius + radius * Math.sqrt(3) / 2, hexRadius, 4);
    }

    /**
     * Dessine une rangée d'hexagones à partir du centre et du rayon spécifiés.
     * 
     * @param g2d       Contexte graphique pour dessiner l'hexagone
     * @param rowX      Position X de la rangée
     * @param rowY      Position Y de la rangée
     * @param radius    Rayon de l'hexagone
     * @param rowLength Longueur de la rangée
     */
    private void drawHexagonRow(Graphics2D g2d, double rowX, double rowY, double radius, int rowLength) {
        int gRadius = tile.getRadius();

        for (int i = 0; i < rowLength; i++) {
            Color[] colors;
            int x = (int) Math.round(rowX + radius * Math.sqrt(3) * i);
            int y = (int) Math.round(rowY);

            if (x == Math.round(gRadius) && y == Math.round(gRadius)) {
                Biome dominantBiome = tile.getDominantBiome();
                colors = (dominantBiome != null) ? dominantBiome.getBiomeColors() : tile.getBiome(TileOrientation.SOUTH).getBiomeColors();
            } else {
                colors = tile.getBiome(tile.determineSide(x, y)).getBiomeColors();
            }

            g2d.setColor(colors[i % colors.length]);
            g2d.fillPolygon(new Hexagon(x, y, (int) Math.ceil(radius), 90));
        }
    }
}
